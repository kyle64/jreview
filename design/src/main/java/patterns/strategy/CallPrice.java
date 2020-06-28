package patterns.strategy;

/**
 * Created by ziheng on 2019-09-17.
 */
public interface CallPrice {
    // 实付价格
    double callPrice(double amount);
}
