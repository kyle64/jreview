package patterns.bridge;

/**
 * Created by ziheng on 2019-09-04.
 */
public class PhoneBridgeTest {
    public static void main(String[] args) {
        PhonePlatform ios = new IOS();
        ios.setApplication(new QQ());
        ios.describe();

        System.out.println("=========================");

        PhonePlatform android = new Android();
        android.setApplication(new WeChat());
        android.describe();
    }
}
