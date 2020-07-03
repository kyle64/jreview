package concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by ziheng on 2020/7/1.
 */
public class ThreadPoolDemo {
    public static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            final int index = i;

            executor.execute(() -> {
                try {
                    Thread.sleep(100 * index);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        Thread.sleep(2000);
        System.out.println("--------------------");
    }
}
