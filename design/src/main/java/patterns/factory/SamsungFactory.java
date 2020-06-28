package patterns.factory;

/**
 * Created by ziheng on 2019-09-02.
 */
public class SamsungFactory implements Factory {
    @Override
    public Phone producePhone() {
        return new SamsungPhone();
    }

    @Override
    public Tablet produceTablet() {
        return null;
    }
}
