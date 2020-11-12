package ratelimit;

/**
 * Created by ziheng on 2020/11/12.
 */
public class LeakyBucket {
    // 漏桶作为计量工具（The Leaky Bucket Algorithm as a Meter）时，可以用于流量整形（Traffic Shaping）和流量控制（TrafficPolicing），漏桶算法的描述如下：
    //
    // 一个固定容量的漏桶，按照常量固定速率流出水滴；
    //
    // 如果桶是空的，则不需流出水滴；
    //
    // 可以以任意速率流入水滴到漏桶；
    //
    // 如果流入水滴超出了桶的容量，则流入的水滴溢出了（被丢弃），而漏桶容量是不变的。

    private int capacity = 10;
    private long currentWater = 0;
    long startTime = System.currentTimeMillis();

    public boolean grant(int rate) {
        long now = System.currentTimeMillis();
        currentWater = Math.max(0, currentWater - (now - startTime) * rate);
        startTime = now;

        if ((currentWater + 1) < capacity) { // 尝试加水,并且水还未满
            currentWater += 1;
            return true;
        } else { // 水满，拒绝加水
            return false;
        }
    }
}
