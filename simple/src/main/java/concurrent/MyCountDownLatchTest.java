package concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Created by ziheng on 2019-08-26.
 */
public class MyCountDownLatchTest {
    public static void main(String[] args) throws Exception {
        CountDownLatch cdl = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread(new MyCdlThread(cdl, i)).start();
        }

        cdl.await();
    }
}

class MyCdlThread implements Runnable {
    private CountDownLatch countDownLatch;
    private int i;

    public MyCdlThread(CountDownLatch countDownLatch, int i) {
        this.countDownLatch = countDownLatch;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(i * 500);
            System.out.println(Thread.currentThread().getName() + " current thread num: " + countDownLatch.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }
}
