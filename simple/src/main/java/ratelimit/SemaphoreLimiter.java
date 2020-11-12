package ratelimit;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by ziheng on 2020/11/12.
 */
public class SemaphoreLimiter {
    // Java 并发库的Semaphore 可以很轻松完成信号量控制，
    // Semaphore可以控制某个资源可被同时访问的个数，通过 acquire() 获取一个许可，如果没有就等待，而 release() 释放一个许可。
    // 单个信号量的Semaphore对象可以实现互斥锁的功能，并且可以是由一个线程获得了“锁”，再由另一个线程释放“锁”，这可应用于死锁恢复的一些场合。
    // 下面的Demo中申明了一个只有5个许可的Semaphore，而有20个线程要访问这个资源，通过acquire()和release()获取和释放访问许可：

    public static ExecutorService threadPool = Executors.newCachedThreadPool();
    public static final int cars = 20;
    public static final int part = 5;

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