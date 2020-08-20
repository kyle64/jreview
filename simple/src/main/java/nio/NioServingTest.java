package nio;

/**
 * Created by ziheng on 2020/8/20.
 */
public class NioServingTest {
    public static void main(String[] args) {
        NioClient nioClient = new NioClient();
        nioClient.startClient("127.0.0.1", 8379, 30 * 1000);
    }
}
