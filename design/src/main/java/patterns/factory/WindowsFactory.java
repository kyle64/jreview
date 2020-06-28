package patterns.factory;

/**
 * Created by ziheng on 2019-09-02.
 */
public class WindowsFactory implements Factory {
    @Override
    public Phone producePhone() {
        return new WindowsPhone();
    }

    @Override
    public Tablet produceTablet() {
        return new Surface();
    }
}
