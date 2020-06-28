package concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by ziheng on 2019-08-27.
 */
public class MyCyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

        for (int i = 0; i < 10; i++) {
            new Thread(new MyCyclicBarrierThread(cyclicBarrier, i)).start();
        }
    }
}

class MyCyclicBarrierThread implements Runnable {
    private CyclicBarrier cyclicBarrier;
    private int i;

    public MyCyclicBarrierThread(CyclicBarrier cyclicBarrier, int i) {
        this.cyclicBarrier = cyclicBarrier;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(i * 500);
            System.out.println(Thread.currentThread().getName() + " current thread num: " + cyclicBarrier.getNumberWaiting());
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + " finish waiting");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}