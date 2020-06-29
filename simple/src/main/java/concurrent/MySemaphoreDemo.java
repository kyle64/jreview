package concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by ziheng on 2020/6/28.
 * 场景：停车场2个车位，10辆车想停，前2辆停完离开后，后2辆才能进入
 * 参考：https://blog.csdn.net/weixin_39389850/java/article/details/103890384
 */
public class MySemaphoreDemo {
    public static ExecutorService threadPool = Executors.newCachedThreadPool();
    public static final int cars = 10;
    public static final int part = 2;

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(cars);
        Semaphore semaphore = new Semaphore(part, false);
        for (int i = 0; i < cars; i++) {
            final int j = i;
            threadPool.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("car " + j + " 进入停车位 \n");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                    latch.countDown();
                    System.out.println("********* car " + j + " 离开停车位\n");
                }
            });
        }
        try {
            latch.await();
            System.out.println("################### 所有车辆离开  ##################");
            threadPool.shutdown();
        } catch (InterruptedException e) {
        }
    }
}
