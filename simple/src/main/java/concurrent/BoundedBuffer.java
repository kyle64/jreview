package concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ziheng on 2020/6/28.
 * Example from {@link Condition} source code
 */
class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                System.out.println("notFull start to wait ...");
                notFull.await();
            }

            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            System.out.println(String.format("current count is %d", count));
            notEmpty.signal();
//            System.out.println("wake up notEmpty");
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
                System.out.println("notEmpty start to wait ...");
            }

            Object x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            System.out.println(String.format("current count is %d", count));
            notFull.signal();
//            System.out.println("wake up notFull");
            return x;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BoundedBuffer boundedBuffer = new BoundedBuffer();

        new Thread(() -> {
            for (int i = 0; i < 150; i++) {
                try {
                    boundedBuffer.put(new Object());
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 150; i++) {
                try {
                    boundedBuffer.take();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
