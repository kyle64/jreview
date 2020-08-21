package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by ziheng on 2020/8/18.
 * 参考：
 * https://blog.csdn.net/haoyuyang/article/details/53231585
 * https://blog.csdn.net/can007/article/details/7514161
 */
public class NioServer implements Runnable {
    private Selector selector;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    public NioServer(int port) {
        try {
            // 1. 打开多路复用器
            selector = Selector.open();
            // 2. 打开服务器通道
            ServerSocketChannel ssc = ServerSocketChannel.open();
            // 3. 设置服务器通道为非阻塞方式
            ssc.configureBlocking(false);
            // 4. 绑定端口
            ssc.bind(new InetSocketAddress(port));
            // 5. 把服务器通道注册到多路复用选择器上，并监听阻塞状态
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server start, port：" + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // 轮询注册在selector上的通道
        while (true) {
            try {
                // 1. 多路复用选择器开始监听
                // 阻塞至有感兴趣的IO事件发生，或者到达超时时间，如果希望一直等待到有感兴趣的
                // 事件发生，可调用无参数的select方法，如果希望不阻塞直接返回目前是否有感兴趣的
                // 事件发生，可调用selectNow方法
                int nkeys = selector.select(1000);

                if (nkeys > 0) {
                    // 2. 返回所有已经注册到多路复用选择器上的通道的SelectionKey
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();

                    // 3. 遍历keys
                    Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();
                        keyIterator.remove();

                        // 判断key的状态是否是有效的
                        // key.isValid()

                        // 如果key是阻塞状态，key对应的channel已经就绪，准备好接受一个新的socket链接时，则调用accept()方法
                        if (key.isValid() && key.isAcceptable()) {
                            accept(key);
                        }
                        // 如果key是可读状态，则调用read()方法
                        if (key.isValid() && key.isReadable()) {
                            read(key);
                        }
                        // 写入事件监听，需要反注册该事件
                        if (key.isValid() && key.isWritable()) {
                            write(key);
                            // 写完就把状态关注去掉，否则会一直触发写事件(改变自身关注事件)
                            // 因为写事件就绪的条件是系统还有空闲的内存，因此会不断触发写事件
                            // 相对应的不注册写事件监听，直接使用channel.write也是可行的
                            key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void accept(SelectionKey selectionKey) {
        try {
            // 1. 获取服务器通道
            ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
            // 2. 接受到这个channel的socket连接
            SocketChannel sc = ssc.accept();
            // 3. 设置阻塞模式为非阻塞
            sc.configureBlocking(false);
            // 4. 注册到多路复用选择器上，并设置读取标识
            sc.register(selector, SelectionKey.OP_READ);
            System.out.println("Server accept connection from " + sc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read(SelectionKey selectionKey) {
        try {
            // 清空缓冲区中的旧数据
            byteBuffer.clear();
            // 获取之前注册的SocketChannel通道
            SocketChannel sc = (SocketChannel) selectionKey.channel();
            // 将sc中的数据放入buffer中
            int count = sc.read(byteBuffer);
            if (count == -1) { // == -1表示通道中没有数据，管道断开连接了
                System.out.println("Channel " + sc + " closed");
                selectionKey.channel().close();
                selectionKey.cancel();
                return;
            }
            // 执行relative get读之前需要flip
            // 执行之前position是在buffer缓冲区的末尾，通过flip方法将position复位到buffer的第一个位置
            byteBuffer.flip();

            // Charset charset = Charset.forName("UTF-8");
            // String receiveStr = new String(charset.decode(byteBuffer).array());
            StringBuilder sb = new StringBuilder();
            while (byteBuffer.hasRemaining()) {
                sb.append((char) byteBuffer.get());
            }
            System.out.println("Server read: " + sb.toString());
            selectionKey.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void write(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        try {
            String response = "3Q-" + System.currentTimeMillis();
            // 获取之前注册的SocketChannel通道
            byteBuffer.clear();
            // 写入buffer缓冲区
            byteBuffer.put(response.getBytes());
            // flip buffer, 准备channel write
            byteBuffer.flip();
            // 将内容写到channel管道中
            socketChannel.write(byteBuffer);
            System.out.println("Server write: " + response);
        } catch (Exception e) {
            e.printStackTrace();
            selectionKey.cancel();
            if (socketChannel != null) {
                socketChannel.close();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new NioServer(8379)).start();
    }
}
