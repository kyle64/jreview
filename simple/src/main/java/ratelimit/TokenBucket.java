package ratelimit;

/**
 * Created by ziheng on 2020/11/12.
 */
public class TokenBucket {
    // 令牌桶和漏桶对比：
    //
    // 令牌桶是按照固定速率往桶中添加令牌，请求是否被处理需要看桶中令牌是否足够，当令牌数减为零时则拒绝新的请求；
    //
    // 漏桶则是按照常量固定速率流出请求，流入请求速率任意，当流入的请求数累积到漏桶容量时，则新流入的请求被拒绝；
    //
    // 令牌桶限制的是平均流入速率（允许突发请求，只要有令牌就可以处理，支持一次拿3个令牌，4个令牌），并允许一定程度突发流量；
    //
    // 漏桶限制的是常量流出速率（即流出速率是一个固定常量值，比如都是1的速率流出，而不能一次是1，下次又是2），从而平滑突发流入速率；
    //
    // 令牌桶允许一定程度的突发，而漏桶主要目的是平滑流入速率；
    //
    // 两个算法实现可以一样，但是方向是相反的，对于相同的参数得到的限流效果是一样的。

    private int capacity = 10;
    private long currentTokenNumer = 0;
    long startTime = System.currentTimeMillis();

    public boolean grant(int rate) {
        long now = System.currentTimeMillis();
        // 先添加令牌
        currentTokenNumer = Math.min(capacity, currentTokenNumer + (now - startTime) * rate);
        startTime = now;
        if (currentTokenNumer < 1) { // 若不到1个令牌,则拒绝
            return false;
        }
        else { // 还有令牌，领取令牌
            currentTokenNumer -= 1;
            return true;
        }
    }
}
