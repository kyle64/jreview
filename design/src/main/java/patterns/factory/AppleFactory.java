package patterns.factory;

/**
 * Created by ziheng on 2019-09-02.
 */
public class AppleFactory implements Factory {
    @Override
    public Phone producePhone() {
        return new IPhone();
    }

    @Override
    public Tablet produceTablet() {
        return new IPad();
    }
}
