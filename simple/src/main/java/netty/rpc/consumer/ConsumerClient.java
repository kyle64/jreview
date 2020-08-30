package netty.rpc.consumer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import netty.rpc.*;
import netty.rpc.decoder.FastJsonDecoder;
import netty.rpc.encoder.FastJsonEncoder;
import netty.rpc.interfaces.ConvertService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ziheng on 2020/8/28.
 */
public class ConsumerClient {
    private final Map<String, ResponseFuture> responseTable = new ConcurrentHashMap<>();

    private String host;
    private int port;
    private EventLoopGroup workerGroup;
    private Bootstrap bootstrap;
    private Channel channel;

    public ConsumerClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.workerGroup = new NioEventLoopGroup();
        this.bootstrap = new Bootstrap();
    }


    public void start() {
        try {
            this.bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new FastJsonDecoder(RemotingType.RESPONSE));
                            pipeline.addLast(new FastJsonEncoder());
                            pipeline.addLast(new ConsumerServiceHandler());
                        }
                    });
            // 连接server
            ChannelFuture future = this.bootstrap.connect(host, port).sync();
            this.channel = future.channel();
//            // 阻塞直到管道关闭
//            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Response send(Request request) throws Exception {
        String requestId = request.getId();

        if (channel != null && channel.isActive()) {
            try {
                final ResponseFuture responseFuture = new ResponseFuture();
                responseFuture.setRequestId(requestId);
                responseFuture.setTimeoutMillis(3000);
                this.responseTable.put(requestId, responseFuture);

                channel.writeAndFlush(request).sync();

//                channel.writeAndFlush(request).addListener(new ChannelFutureListener() {
//                    @Override
//                    public void operationComplete(ChannelFuture future) throws Exception {
//                        if (future.isSuccess()) {
//                            System.out.println(channel + " Request " + request.getId() + " 发送成功");
//                            return;
//                        } else {
//                            System.out.println(channel + " Request " + request.getId() + " 发送失败");
//                        }
//                        responseTable.remove(request.getId());
//                    }
//                });

                Response response = responseFuture.waitForResponse(3000);
                return response;
            } finally {
                this.responseTable.remove(requestId);
            }
        } else {
            throw new Exception("channel " + channel + " is inactive");
        }
    }

    public void shutdown() {
        this.workerGroup.shutdownGracefully();
    }

    class ConsumerServiceHandler extends SimpleChannelInboundHandler<Response> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Response msg) throws Exception {
            ResponseFuture responseFuture = responseTable.get(msg.getRequestId());
            System.out.println(channel + " received data from server, request id: " + msg.getRequestId());
            if (responseFuture != null) {
                responseFuture.setResponseContent(msg);
                responseTable.remove(msg.getRequestId());
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
    }

    public static void main(String[] args) {
        ConvertService convertService = (ConvertService) ConsumerProxy.create(ConvertService.class);
        String result = convertService.base64Encrypt("123");
        System.out.println(result);
    }
}
