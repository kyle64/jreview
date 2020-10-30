package concurrent.synchronizedLock;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ziheng on 2020/10/30.
 */
public class BiasedLockDemo {
    public static void main(String[] args) throws Exception {
        //延时产生可偏向对象
        Thread.sleep(5000);

        //创造100个偏向线程t1的偏向锁
        List<A> listA = new ArrayList<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i <100 ; i++) {
                A a = new A();
                synchronized (a){
                    listA.add(a);
                }
            }
            try {
                //为了防止JVM线程复用，在创建完对象后，保持线程t1状态为存活
                Thread.sleep(100000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        //睡眠3s钟保证线程t1创建对象完成
        Thread.sleep(3000);
        // 创造了100个偏向线程t1的偏向锁，结果没什么好说的，100个偏向锁嘛
        System.out.println("打印t1线程，list中第20个对象的对象头：");
        System.out.println((ClassLayout.parseInstance(listA.get(19)).toPrintable()));

        //创建线程t2竞争线程t1中已经退出同步块的锁
        Thread t2 = new Thread(() -> {
            //这里面只循环了30次！！！
            for (int i = 0; i < 30; i++) {
                A a =listA.get(i);
                synchronized (a){
                    //分别打印第19次和第20次偏向锁重偏向结果
                    if(i==18||i==19){
                        // 再来看看重偏向的结果，线程t2，前19次偏向均产生了轻量锁，
                        // 而到第20次的时候，达到了批量重偏向的阈值20，
                        // 此时锁并不是轻量级锁，而变成了偏向锁，此时偏向的线程t2
                        System.out.println("第"+ ( i + 1) + "次偏向结果");
                        System.out.println((ClassLayout.parseInstance(a).toPrintable()));
                    }
                }
            }
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();

        Thread.sleep(3000);
        // 前20个对象，并没有触发了批量重偏向机制，线程t2执行释放同步锁(轻量级锁)后，转变为无锁形态
        System.out.println("打印list中第11个对象的对象头：");
        System.out.println((ClassLayout.parseInstance(listA.get(10)).toPrintable()));
        // 20~30个对象，触发了批量重偏向机制，对象为偏向锁状态，偏向线程t2
        System.out.println("打印list中第26个对象的对象头：");
        System.out.println((ClassLayout.parseInstance(listA.get(25)).toPrintable()));
        // 而31个对象之后，也没有触发了批量重偏向机制，对象仍偏向线程t1
        System.out.println("打印list中第41个对象的对象头：");
        System.out.println((ClassLayout.parseInstance(listA.get(40)).toPrintable()));
    }
}
