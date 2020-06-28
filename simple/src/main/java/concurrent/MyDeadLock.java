package concurrent;

/**
 * Created by ziheng on 2019-08-28.
 */
public class MyDeadLock {
    final Object objectA = new Object();
    final Object objectB = new Object();

    public static void main(String[] args) {
        MyDeadLock myDeadLock = new MyDeadLock();

        new Thread(new DThread1(myDeadLock)).start();
        new Thread(new DThread2(myDeadLock)).start();
    }
}

class DThread1 implements Runnable {
    MyDeadLock myDeadLock;

    public DThread1(MyDeadLock myDeadLock) {
        this.myDeadLock = myDeadLock;
    }

    @Override
    public void run() {
        synchronized (myDeadLock.objectA) {
            try {
                System.out.println(Thread.currentThread().getName() + " get A");
                Thread.sleep(3000);
                // try to get access obj B
                synchronized (myDeadLock.objectB) {
                    System.out.println(Thread.currentThread().getName() + " get B");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class DThread2 implements Runnable {
    MyDeadLock myDeadLock;

    public DThread2(MyDeadLock myDeadLock) {
        this.myDeadLock = myDeadLock;
    }

    @Override
    public void run() {
        synchronized (myDeadLock.objectB) {
            try {
                System.out.println(Thread.currentThread().getName() + " get B");
                Thread.sleep(3000);
                // try to get access obj B
                synchronized (myDeadLock.objectA) {
                    System.out.println(Thread.currentThread().getName() + " get A");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
