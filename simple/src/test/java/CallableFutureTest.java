import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by ziheng on 2019-08-15.
 */
public class CallableFutureTest {
    private ExecutorService executorService;

    @Before
    public void init() {
        executorService = Executors.newFixedThreadPool(5);
    }

    @Test
    public void callableTest() throws ExecutionException, InterruptedException {
        Future<String> task = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " start");
                Thread.sleep(5000);
                return Thread.currentThread().getName() + " content";
            }
        });

        System.out.println(task.get());
        executorService.shutdown();
    }

    @Test
    public void callableListTest() throws InterruptedException, ExecutionException {
        List<Callable<String>> callableList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            callableList.add(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println(Thread.currentThread().getName() + " start");
                    Thread.sleep(500);
                    return Thread.currentThread().getName() + " content";
                }
            });
        }

        List<Future<String>> futureList = executorService.invokeAll(callableList);
        System.out.println("all tasks submitted");

        for (Future<String> stringFuture : futureList) {
            System.out.println(stringFuture.get());
        }
    }

    @Test
    public void futureTaskTest() throws InterruptedException, ExecutionException {
        List<FutureTask<String>> taskList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println(Thread.currentThread().getName() + " start");
                    Thread.sleep(500);
                    return Thread.currentThread().getName() + " content";
                }
            });

            executorService.submit(futureTask);
            taskList.add(futureTask);
        }

        System.out.println("all tasks submitted");
        Thread.sleep(3000);

        for (FutureTask<String> futureTask : taskList) {
            try {
                System.out.println(futureTask.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
