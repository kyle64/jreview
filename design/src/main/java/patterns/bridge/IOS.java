package patterns.bridge;

/**
 * Created by ziheng on 2019-09-04.
 */
public class IOS extends PhonePlatform {
    @Override
    public void describe() {
        System.out.println("IOS platform");
        this.application.show();
    }
}
