package nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by ziheng on 2020/8/18.
 */
public class NioClient {
    private Selector selector;
    private SocketChannel socketChannel;
//    private ByteBuffer byteBuffer;
    private boolean stop;

    public NioClient() {
        try {
            // 打开多路复用器
            selector = Selector.open();
            // 打开通道
            socketChannel = SocketChannel.open();
            // 设置为非阻塞模式
            socketChannel.configureBlocking(false);
//            // 创建buffer缓冲区
//            byteBuffer = ByteBuffer.allocate(1024);
            stop = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startClient(String ipStr, int port) {
        this.startClient(ipStr, port, -1);
    }

    public void startClient(String ipStr, int port, int timeout) {
        try {
            connect(ipStr, port);
            Long startTime = System.currentTimeMillis();

            while (!stop) {

                int nkeys = selector.select(1000);
                if (nkeys > 0) {
                    // 返回所有已经注册到多路复用选择器上的通道的SelectionKey
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();

                    // 遍历keys
                    Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
                    // 有事件发生
                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();
                        SocketChannel client = (SocketChannel) key.channel();
                        client.configureBlocking(false);
                        // remove处理过的key
                        keyIterator.remove();

                        // 可连接的事件
                        if (key.isConnectable()) {
                            // finishConnect()方法会阻塞到链接结束并返回是否成功
                            // isConnectionPending()返回的是是否处于正在连接状态(还在三次握手中)
                            if (client.finishConnect()) {
                                // 向多路复用器注册可读事件
                                client.register(selector, SelectionKey.OP_READ);
                                // 向管道写数据
                                new Thread(() -> {
                                    while (true) {
                                        try {
                                            doWrite(client);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }).start();

                            } else {
                                System.exit(1);
                            }
                        }

                        // 读取服务端的响应
                        if (key.isReadable()) {
                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                            int readBytes = client.read(readBuffer);
                            if (readBytes > 0) {
                                readBuffer.flip();
                                byte[] bytes = new byte[readBuffer.remaining()];
                                readBuffer.get(bytes);
                                System.out.println("client received: " + new String(bytes));
                            } else if (readBytes < 0) {
                                // 对端链路关闭
                                key.channel();
                                client.close();
                            }
                        }

                    }
                }

                if (timeout > 0 && System.currentTimeMillis() - startTime > timeout) {
                    shutdown();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connect(String ipStr, int port) throws IOException {
        // 建立连接
        InetSocketAddress address = new InetSocketAddress(ipStr, port);
        if (socketChannel.connect(address)) {
            // 如果成功，说明连接已经建立过了，则向多路复用器注册可读事件
            socketChannel.register(selector, SelectionKey.OP_READ);
            // 写消息
            doWrite(socketChannel);
        } else {
            // 如果连接尚未建立，则向多路复用器注册连接事件
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }

    }

    public void shutdown() throws IOException {
        stop = true;
        socketChannel.keyFor(selector).cancel();
        socketChannel.close();
        selector.close();
        System.exit(1);
    }

    private void doWrite(SocketChannel socketChannel) throws IOException {
        // 输入要写的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        ByteBuffer byteBuffer = ByteBuffer.allocate(line.length());

        byteBuffer.clear();
        // 写入buffer缓冲区
        byteBuffer.put(line.getBytes());
        // flip buffer, 准备channel write
        byteBuffer.flip();
        // 将内容写到channel管道中
        socketChannel.write(byteBuffer);
    }
}
