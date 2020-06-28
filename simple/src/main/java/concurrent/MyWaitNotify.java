package concurrent;

/**
 * Created by ziheng on 2019-08-14.
 */

public class MyWaitNotify {

    Object myMonitorObject = new Object();

    public void doWait() {
        synchronized (myMonitorObject) {
            try {
                myMonitorObject.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void doNotify() {
        synchronized (myMonitorObject) {
            myMonitorObject.notify();
        }
    }

    public static void main(String[] args) {
        final MyWaitNotify myWaitNotify = new MyWaitNotify();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "start waiting ");
                myWaitNotify.doWait();
                System.out.println(Thread.currentThread().getName() + "finish waiting ");
            }
        });

        thread1.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + "start notifying");
                    myWaitNotify.doNotify();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
