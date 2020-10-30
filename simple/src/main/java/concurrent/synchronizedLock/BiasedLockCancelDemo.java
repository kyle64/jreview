package concurrent.synchronizedLock;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ziheng on 2020/10/30.
 * 1、批量重偏向和批量撤销是针对类的优化，和对象无关。
 * 2、偏向锁重偏向一次之后不可再次重偏向。
 * 3、当某个类已经触发批量撤销机制后，JVM会默认当前类产生了严重的问题，剥夺了该类的新实例对象使用偏向锁的权利
 */
public class BiasedLockCancelDemo {
    public static void main(String[] args) throws Exception {

        Thread.sleep(5000);
        List<A> listA = new ArrayList<>();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i <100 ; i++) {
                A a = new A();
                synchronized (a){
                    listA.add(a);
                }
            }
            try {
                Thread.sleep(100000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        Thread.sleep(3000);

        Thread t2 = new Thread(() -> {
            //这里循环了40次。达到了批量撤销的阈值
            for (int i = 0; i < 40; i++) {
                A a =listA.get(i);
                synchronized (a){
                }
            }
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();

        //———————————分割线，前面代码不再赘述——————————————————————————————————————————
        Thread.sleep(3000);
        System.out.println("打印list中第11个对象的对象头：");
        System.out.println((ClassLayout.parseInstance(listA.get(10)).toPrintable()));
        System.out.println("打印list中第26个对象的对象头：");
        System.out.println((ClassLayout.parseInstance(listA.get(25)).toPrintable()));
        System.out.println("打印list中第90个对象的对象头：");
        System.out.println((ClassLayout.parseInstance(listA.get(89)).toPrintable()));


        Thread t3 = new Thread(() -> {
            for (int i = 20; i < 40; i++) {
                A a =listA.get(i);
                // 重头戏来了！线程t3也来竞争锁。
                // 因为已经达到了批量撤销的阈值，且对象listA.get(20)和listA.get(22)已经进行过偏向锁的重偏向，并不会再次重偏向线程t3。
                // 此时触发批量撤销，此时对象锁膨胀变为轻量级锁。
                synchronized (a){
                    if(i==20||i==22){
                        System.out.println("thread3 第"+ i + "次");
                        System.out.println((ClassLayout.parseInstance(a).toPrintable()));
                    }
                }
            }
        });
        t3.start();


        Thread.sleep(10000);
        System.out.println("重新输出新实例A");
        // 再来看看最后新生成的对象A。值得注意的是：本应该为可偏向状态偏向锁的新对象，在经历过批量重偏向和批量撤销后直接在实例化后转为无锁。
        System.out.println((ClassLayout.parseInstance(new A()).toPrintable()));
    }
}
