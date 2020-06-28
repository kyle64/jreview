package concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by ziheng on 2019-08-15.
 */
public class MyExecutor {

    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
    private static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
    private static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {

    }
}
