package concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by ziheng on 2019-08-26.
 */
public class MyReadWriteLockTest {
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static Lock readLock = lock.readLock();
    private static Lock writeLock = lock.writeLock();
    private static Map<String, Object> map = new HashMap<>();

    public static Integer get(Map<String, Object> map, int i) {
        System.out.println("get ..................");
        Integer getValue = -1;
        if (map.size() < 0) {
            //pass
            return getValue;
        } else {
            System.out.println(Thread.currentThread().getName() + "-上读锁");
            readLock.lock();

            System.out.println(Thread.currentThread().getName() + "-获取读锁");
            getValue = (Integer) map.get("key" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			readLock.unlock();
            System.out.println(Thread.currentThread().getName() + "-释放读锁");
        }
        return getValue;
    }

    public static Integer put(Map<String, Object> map, int i) {
        System.out.println("put ..................");
        Integer putValue = -1;
        if (map.size() > 50) {
            return putValue;
        } else {
            System.out.println(Thread.currentThread().getName() + "-上写锁");
            writeLock.lock();

            System.out.println(Thread.currentThread().getName() + "-获取写锁");
            map.put("key" + i, i + 1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            putValue = i + 1;
			writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + "-释放写锁");
        }
        return putValue;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            map.put("key" + i, i + 1);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // read & read
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(30);
                        get(map, 0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(30);
                        get(map, 0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }

}
