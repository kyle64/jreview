package netty.heartbeat;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created by ziheng on 2020/8/26.
 */
public class HeartbeatClient {
    private String host;
    private int port;
    private EventLoopGroup workerGroup;
    private Bootstrap bootstrap;
    private Channel channel;

    public HeartbeatClient(String host, int port) {
        this.host = host;
        this.port = port;
        init();
    }

    private void init() {
        workerGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                        ch.pipeline().addLast(new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS));
                        ch.pipeline().addLast("decoder", new StringDecoder());
                        ch.pipeline().addLast("encoder", new StringEncoder());
                        ch.pipeline().addLast(new HeartbeatClientHandler());
                    }
                });
    }

    public void connect() {
        ChannelFuture channelFuture = bootstrap.connect(host, port);
        // 监听通道连接
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (channelFuture.isSuccess()) {
                    System.out.println("连接成功");
                    channel = future.channel();
                } else {
                    System.out.println("每隔2s重连....");
                    channelFuture.channel().eventLoop().schedule(new Runnable() {
                        @Override
                        public void run() {
                            connect();
                        }
                    }, 2, TimeUnit.SECONDS);
                }
            }
        });
    }

    public void write(String msg) throws Exception {
        while (channel != null) {
            Thread.sleep(3000);
        }

        Thread.sleep(5000);
        channel.writeAndFlush(msg);
        System.out.println("Client send: " + msg);

        channel.closeFuture().sync();
    }

    public void shutdown() {
        workerGroup.shutdownGracefully();
    }

    public Channel getChannel() {
        return channel;
    }

    public static void main(String[] args) throws Exception {
        HeartbeatClient client = new HeartbeatClient("127.0.0.1", 8379);
        try {
            client.connect();
            String msg = "Hello Netty!";
            client.write(msg);
            client.getChannel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.shutdown();
        }

//        String host = "127.0.0.1";
//        int port = 8379;
//
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        try {
//            Bootstrap bootstrap = new Bootstrap();
//            bootstrap.group(workerGroup)
//                    .channel(NioSocketChannel.class)
//                    .handler(new ChannelInitializer<SocketChannel>() {
//                        @Override
//                        protected void initChannel(SocketChannel ch) throws Exception {
////                            ch.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
//                            ch.pipeline().addLast(new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS));
//                            ch.pipeline().addLast("decoder", new StringDecoder());
//                            ch.pipeline().addLast("encoder", new StringEncoder());
//                            ch.pipeline().addLast(new HeartbeatClientHandler());
//                        }
//                    });
//            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
//
//            String msg = "Hello Netty!";
//            channelFuture.channel().writeAndFlush(msg);
//            System.out.println("Client send: " + msg);
//
//            channelFuture.channel().closeFuture().sync();
//        } finally {
//            workerGroup.shutdownGracefully();
//        }

    }
}
