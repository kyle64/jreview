package nio;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ziheng on 2020/8/21.
 */
public class AioServer {
    //线程池
    private ExecutorService executorService;
    //线程组
    private AsynchronousChannelGroup channelGroup;
    //服务器通道
    public AsynchronousServerSocketChannel channel;

    public AioServer(int port) {
        try {
            //创建线程池
            executorService = Executors.newCachedThreadPool();
            //创建线程组
            channelGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
            //创建服务器通道
            channel = AsynchronousServerSocketChannel.open(channelGroup);
            //绑定地址
            channel.bind(new InetSocketAddress(port));
            System.out.println("server start, port：" + port);
            //进行阻塞
            channel.accept(this, new ServerCompletionHandler());
            //一直阻塞 不让服务器停止
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AioServer aioServer = new AioServer(8380);
    }
}
