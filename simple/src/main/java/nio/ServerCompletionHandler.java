package nio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created by ziheng on 2020/8/21.
 */
public class ServerCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AioServer> {
    @Override
    public void completed(AsynchronousSocketChannel channel, AioServer attachment) {
        //当有下一个客户端接入的时候，直接调用Server的accept方法，这样反复执行下去，保证多个客户端都可以阻塞
        //（没有递归上限），1.7以后AIO才实现了异步非阻塞
        attachment.channel.accept(attachment, this);
        read(channel);
    }

    private void read(AsynchronousSocketChannel channel) {
        //读取数据
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer resultSize, ByteBuffer attachment) {
                //进行读取之后,重置标识位
                attachment.flip();
                //获得读取的字节数
                System.out.println("Server->" + "收到客户端发送的数据长度为：" + resultSize);
                //获取读取的数据
                String data = new String(buffer.array()).trim();
                System.out.println("Server->" + "收到客户端发送的数据为：" + data);
                String response = "服务器端响应了客户端。。。。。。";
                write(channel, response);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });
    }

    private void write(AsynchronousSocketChannel channel, String response) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(response.getBytes());
            buffer.flip();
            channel.write(buffer).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, AioServer attachment) {
        exc.printStackTrace();
    }
}
