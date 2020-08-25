package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by ziheng on 2020/8/24.
 */
public class HelloWorldClient {
    private String host;
    private int port;
    private EventLoopGroup workerGroup;
    private Bootstrap bootstrap;

    public HelloWorldClient(String host, int port) {
        this.host = host;
        this.port = port;
        init();
    }

    private void init() {
        workerGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
//                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new HelloWorldClientHandler());
                    }
                });

    }

    public void write(String msg) throws Exception {
        ChannelFuture future = bootstrap.connect(host, port).sync();
        ByteBuf byteBuf = Unpooled.buffer(128);
        byteBuf.writeBytes(msg.getBytes());
        future.channel().writeAndFlush(byteBuf);
        // 阻塞，服务端的这句代码才会往下执行
        future.channel().closeFuture().sync();
    }

    public void shutdown() {
        workerGroup.shutdownGracefully();
    }

    public static void main(String[] args) {
        HelloWorldClient client = new HelloWorldClient("127.0.0.1", 8379);
        try {
            String msg = "hello, I'm client1";
            client.write(msg);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.shutdown();
        }
    }
}
