package patterns.observer;

/**
 * Created by ziheng on 2019-09-20.
 */
public class FundManager1 implements Observer {
    private static final double SELL_RATE = 0.02;
    private static final double BUY_RATE = -0.03;

    @Override
    public void operate(String subject, double percent) {
        if (percent > SELL_RATE) {
            System.out.println("基金1卖出 " + subject);
        } else if (percent < BUY_RATE ) {
            System.out.println("基金1买入 " + subject);
        } else {
            System.out.println("基金1观望 " + subject);
        }
    }
}
