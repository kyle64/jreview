package concurrent;

import java.util.concurrent.BlockingQueue;

/**
 * Created by ziheng on 2019-08-15.
 */
public class MyConsumer implements Runnable {
    private BlockingQueue<Object> queue;

    public MyConsumer(BlockingQueue<Object> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object object = queue.take();
                System.out.println("消费者 资源 队列大小 " + queue.size());
                Thread.sleep(100);
                System.out.println("消费对象 " + object);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
