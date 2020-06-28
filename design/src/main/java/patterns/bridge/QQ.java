package patterns.bridge;

/**
 * Created by ziheng on 2019-09-04.
 */
public class QQ implements PhoneApplication {
    @Override
    public void show() {
        System.out.println("QQ application");
    }
}
