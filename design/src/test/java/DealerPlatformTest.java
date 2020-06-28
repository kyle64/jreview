import org.junit.Test;
import patterns.proxy.Dealer;
import patterns.proxy.DealerA;
import patterns.proxy.DealerB;
import patterns.proxy.DynamicProxyPlatform;

/**
 * Created by ziheng on 2019-08-07.
 */
public class DealerPlatformTest {

    @Test
    public void displayTest() {
        Dealer dealerA = new DynamicProxyPlatform().getProxyInstance(new DealerA());
        dealerA.display();

//        Dealer dealerB = new DynamicProxyPlatform().getProxyInstance(new DealerB());
//        dealerB.display();
    }
}
