package concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ziheng on 2019/4/7.
 */
public class MyThread {
    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();

        for (int i = 0; i < 10; i++) {
//            concurrent.MyThread1 thread1 = new concurrent.MyThread1();
            Thread thread2 = new Thread(new MyThread2());
//            thread1.start();
            thread2.start();

            Thread spinLockThread = new Thread(new MySpinLockThread(spinLock));
            spinLockThread.start();
        }
    }
}

class MyReentrantLockThread implements Runnable {
    private Lock lock = new ReentrantLock(true);

    @Override
    public void run() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": get concurrent.lock");
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + ": release concurrent.lock");
        }
    }
}

class MySynchronizedThread implements Runnable {

    @Override
    public void run() {
        try {
            synchronized (this) {
                for (int i = 0; i < 3; i++) {
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MySpinLockThread implements Runnable {
    private SpinLock spinLock;

    public MySpinLockThread(SpinLock spinLock) {
        this.spinLock = spinLock;
    }

    @Override
    public void run() {
        try {
            spinLock.lock();
            for (int i = 0; i < 3; i++) {
                System.out.println(getClass().getSimpleName() + " " + Thread.currentThread().getName() + ": " + i);
                Thread.sleep(50);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            spinLock.unlock();
        }
    }
}

class MyThread2 implements Runnable {
    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println(getClass().getSimpleName() + " " + Thread.currentThread().getName() + ": " + i);
                Thread.sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyThread1 extends Thread {
    public void run() {
        try {
            System.out.println(getClass().getSimpleName() + " " + Thread.currentThread().getName());
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}