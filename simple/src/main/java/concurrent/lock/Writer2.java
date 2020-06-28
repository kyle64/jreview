package concurrent.lock;

/**
 * Created by ziheng on 2019-08-19.
 */
public class Writer2 extends Thread {
    private BufferInterruptibly buff;

    public Writer2(BufferInterruptibly buff) {
        this.buff = buff;
    }

    @Override
    public void run() {
        buff.write();
    }
}
