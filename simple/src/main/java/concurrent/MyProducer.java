package concurrent;

import java.util.concurrent.BlockingQueue;

/**
 * Created by ziheng on 2019-08-15.
 */
public class MyProducer implements Runnable {
    private BlockingQueue<Object> queue;

    public MyProducer(BlockingQueue<Object> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(100);

                Object object = new Object();
                queue.put(object);
                System.out.println("生产对象: " + object);
                System.out.println("生产者资源队列大小= " + queue.size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
