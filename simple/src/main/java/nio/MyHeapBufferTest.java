package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.*;
import java.nio.channels.FileChannel;

/**
 * Created by ziheng on 2020/8/18.
 */
public class MyHeapBufferTest {
    public static void main(String[] args) {
        String input = "hello world.";

        // 1、基本操作
        // 创建指定长度的缓冲区
        ByteBuffer heapByteBuffer = ByteBuffer.allocate(32);
        for (byte b : input.getBytes()) {
            heapByteBuffer.put(b);
        }

        System.out.println("未调用flip复位方法前的buffer：" + heapByteBuffer);

        // 把位置复位为0，也就是position位置由12->0
        heapByteBuffer.flip();
        // 比较未调用flip方法和调用之后buffer的limit可以发现，不进行复位操作的话，position的值为12，limit的值为32
        System.out.println("调用flip复位方法后的buffer：" + heapByteBuffer);
        System.out.println("buffer容量为：" + heapByteBuffer.capacity());
        System.out.println("buffer限制为：" + heapByteBuffer.limit());
        System.out.println("获取下标为1的元素：" + heapByteBuffer.get(1));
        System.out.println("调用get(index)方法后的buffer：" + heapByteBuffer); //调用get(index)方法，不会改变position的值

        heapByteBuffer.put(11, (byte) '!');

        for (int i = 0; i < heapByteBuffer.limit(); i++) {
            // 调用get方法会使缓冲区的位置(position)向后递增一位
            System.out.print((char) heapByteBuffer.get() + "\t");
        }

        System.out.println("\nbuffer对象遍历之后buffer为：" + heapByteBuffer);

        // 2、wrap方法的使用
        int[] arr = new int[]{1, 2, 3};
        IntBuffer buffer = IntBuffer.wrap(arr);
        System.out.println("wrap(arr)方法：" + buffer);
        // IntBuffer.wrap(array, postion, length)表示容量为array的长度，但是可操作的元素为位置postion到length的数组元素
        buffer = IntBuffer.wrap(arr, 0, 2);
        System.out.println("wrap(arr, 0, 2)：" + buffer);

        // 测试file channel读写
        FileChannel fc = null;
        try {
            // write
            fc = new FileOutputStream("simple/helloworld.txt").getChannel();
            heapByteBuffer.flip();
            fc.write(heapByteBuffer);
            fc.close();

            System.out.println("write之后的的buffer：" + heapByteBuffer);

            // read
            fc = new FileInputStream("simple/helloworld.txt").getChannel();
            heapByteBuffer.clear();
            fc.read(heapByteBuffer);

            System.out.println("read之后的的buffer：" + heapByteBuffer);

            heapByteBuffer.flip();
            for (int i = 0; i < heapByteBuffer.limit(); i++) {
                // 调用get方法会使缓冲区的位置(position)向后递增一位
                System.out.print((char) heapByteBuffer.get() + "\t");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fc != null) {
                try {
                    fc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
