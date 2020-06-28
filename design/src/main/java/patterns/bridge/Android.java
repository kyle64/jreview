package patterns.bridge;

/**
 * Created by ziheng on 2019-09-04.
 */
public class Android extends PhonePlatform {
    @Override
    public void describe() {
        System.out.println("Android platform");
        this.application.show();
    }
}
