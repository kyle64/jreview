package concurrent;

/**
 * Created by ziheng on 2019-08-19.
 */
public class MyLock {

    public synchronized void syncTest() {
        try {
            System.out.println(Thread.currentThread().getName() + ": get concurrent.lock");
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + ": release concurrent.lock");
        }
    }

    public synchronized void syncTest2() {
        try {
            System.out.println(Thread.currentThread().getName() + ": get concurrent.lock 2");
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + ": release concurrent.lock 2");
        }
    }

    public static void main(String[] args) {
        // reentrant concurrent.lock is towards object
//        Runnable t1 = new concurrent.MyReentrantLockThread();
//        new Thread(t1, "t1").start();
//        new Thread(t1, "t2").start();
//        new Thread(t1, "t3").start();

        // synchronized non-static method is towards object
        MyLock myLock = new MyLock();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> myLock.syncTest()).start();
            new Thread(() -> myLock.syncTest2()).start();
        }
    }

}
