package patterns.facade;

/**
 * Created by ziheng on 2019-09-06.
 */
public class PaymentSystem {
    public void pay() {
        System.out.println("make payment to hotel");
    }

    public void refund() {
        System.out.println("refund deposit to customer");
    }
}
