package patterns.bridge;

/**
 * Created by ziheng on 2019-09-04.
 */
public abstract class PhonePlatform {
    protected PhoneApplication application;

    public void setApplication(PhoneApplication application) {
        this.application = application;
    }

    public abstract void describe();
}
