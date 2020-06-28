package patterns.factory;

/**
 * Created by ziheng on 2019-09-02.
 */
public class IPhoneFactory implements PhoneFactory {
    @Override
    public Phone producePhone() {
        return new IPhone();
    }
}
