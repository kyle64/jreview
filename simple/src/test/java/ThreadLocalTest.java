import concurrent.MyContextHolder;
import org.junit.Test;

/**
 * Created by ziheng on 2019-08-16.
 */
public class ThreadLocalTest {

    @Test
    public void threadLocalTest() {
        for (int i = 0; i < 10; i++) {
            final Integer count = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + " " + MyContextHolder.getCount());
                        MyContextHolder.setCount(count);
                        System.out.println(Thread.currentThread().getName() + " " + MyContextHolder.getCount());
                    } finally {
                        MyContextHolder.clear();
                    }
                }
            }).start();
        }
    }
}
