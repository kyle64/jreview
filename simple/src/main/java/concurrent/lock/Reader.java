package concurrent.lock;

/**
 * Created by ziheng on 2019-08-19.
 */
public class Reader extends Thread {
    private Buffer buffer;

    public Reader(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            buffer.read();//可以收到中断的异常，从而有效退出
        } catch (Exception e) {
            System.out.println("我不读了");
        }

        System.out.println("读结束");
    }
}
