package concurrent;

/**
 * Created by ziheng on 2019-08-14.
 */
public class MyWaitNotify3 {

    private final Object myMonitorObject = new Object();

    private boolean wasSignalled = false;

    public void doWait() {
        synchronized (myMonitorObject) {
            while (!wasSignalled) {
                try {
                    System.out.println(Thread.currentThread().getName() + "start waiting ");
                    myMonitorObject.wait();
                    System.out.println(Thread.currentThread().getName() + "finish waiting ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //clear signal and continue running.
            wasSignalled = false;
        }
    }

    public void doNotify() {
        synchronized (myMonitorObject) {
            wasSignalled = true;
            myMonitorObject.notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        final MyWaitNotify3 myWaitNotify = new MyWaitNotify3();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    myWaitNotify.doWait();
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + "start notifying");
                myWaitNotify.doNotify();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
