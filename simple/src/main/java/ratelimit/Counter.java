package ratelimit;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ziheng on 2020/11/12.
 * 计数器算法是限流算法里最简单也是最容易实现的一种算法。
 * 比如我们规定，对于A接口来说，我们1分钟的访问次数不能超过100个。
 * 那么我们可以这么做：在一开 始的时候，我们可以设置一个计数器counter，
 * 每当一个请求过来的时候，counter就加1，
 * 如果counter的值大于100并且该请求与第一个 请求的间隔时间还在1分钟之内，那么说明请求数过多；
 * 如果该请求与第一个请求的间隔时间大于1分钟，且counter的值还在限流范围内，那么就重置 counter
 */
public class Counter {
    AtomicInteger count = new AtomicInteger(0);
    long startTime = System.currentTimeMillis();
    public boolean grant(int limit, long interval) {
        long now = System.currentTimeMillis();
        if (now < startTime + interval) { // 在间隔时间内
            count.getAndIncrement();
            return count.intValue() < limit;
        } else {
            // 重置counter
            count.set(0);
            startTime = now;
            return true;
        }
    }
}
