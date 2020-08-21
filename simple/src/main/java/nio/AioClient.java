package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

/**
 * Created by ziheng on 2020/8/21.
 */
public class AioClient implements Runnable {

    private AsynchronousSocketChannel channel;

    public AioClient() throws IOException {
        channel = AsynchronousSocketChannel.open();
    }

    public void connect() {
        channel.connect(new InetSocketAddress("127.0.0.1", 8380));
    }

    public void write(String data) {
        try {
            channel.write(ByteBuffer.wrap(data.getBytes())).get();
            read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void read() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            channel.read(buffer).get();
            buffer.flip();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            String data = new String(bytes, "UTF-8").trim();
            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {

        }
    }

    public static void main(String[] args) {
        try {
            AioClient c1 = new AioClient();
            AioClient c2 = new AioClient();
            AioClient c3 = new AioClient();

            c1.connect();
            c2.connect();
            c3.connect();

            new Thread(c1).start();
            new Thread(c2).start();
            new Thread(c3).start();

            Thread.sleep(1000);

            c1.write("c1 aaa");
            c2.write("c2 bbbb");
            c3.write("c3 ccccc");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
