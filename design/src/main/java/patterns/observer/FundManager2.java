package patterns.observer;

/**
 * Created by ziheng on 2019-09-24.
 */
public class FundManager2 implements Observer {
    private static final double SELL_RATE = 0.01;
    private static final double BUY_RATE = -0.02;

    @Override
    public void operate(String subject, double percent) {
        if (percent > SELL_RATE) {
            System.out.println("基金2卖出 " + subject);
        } else if (percent < BUY_RATE ) {
            System.out.println("基金2买入 " + subject);
        } else {
            System.out.println("基金2观望 " + subject);
        }
    }
}
