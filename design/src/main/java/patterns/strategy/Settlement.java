package patterns.strategy;

/**
 * Created by ziheng on 2019-09-17.
 */
public class Settlement { // 环境类
    private double totalAmount = 0;
    private CallPrice callPrice; // 策略类

    public Settlement() {
        this(0d);
    }

    public Settlement(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double buy(double origin) {
        this.totalAmount += origin;
        this.callPrice = PriceStrategyFactory.getInstance().getCallPriceByAnnotation(this.totalAmount);

//        if (this.totalAmount < 5000) {
//            this.callPrice = new NormalCustomer();
//        } else if (this.totalAmount < 10000) {
//            this.callPrice = new Member();
//        } else if (this.totalAmount < 30000) {
//            this.callPrice = new GoldMember();
//        } else {
//            this.callPrice = new DiamondMember();
//        }

        return callPrice.callPrice(origin);

    }

    public static void main(String[] args) {
        Settlement settlement = new Settlement();
        System.out.println("需支付：" + settlement.buy(2000));
        System.out.println("需支付：" + settlement.buy(3000));
        System.out.println("需支付：" + settlement.buy(5000));
        System.out.println("需支付：" + settlement.buy(20000));
    }
}
