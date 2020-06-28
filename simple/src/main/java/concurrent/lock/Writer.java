package concurrent.lock;

/**
 * Created by ziheng on 2019-08-19.
 */
public class Writer extends Thread {
    private Buffer buffer;

    public Writer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            buffer.write();
        } catch (Exception e) {
            System.out.println("我不写了");
        }

        System.out.println("写结束");
    }
}
