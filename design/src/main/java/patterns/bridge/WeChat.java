package patterns.bridge;

/**
 * Created by ziheng on 2019-09-04.
 */
public class WeChat implements PhoneApplication {
    @Override
    public void show() {
        System.out.println("WeChat application");
    }
}
