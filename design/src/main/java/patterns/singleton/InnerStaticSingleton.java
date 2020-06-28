package patterns.singleton;

/**
 * Created by ziheng on 2019-08-30.
 */
public class InnerStaticSingleton {
    private static final class SingletonHolder {
        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }

    private InnerStaticSingleton() {
    }

    public static InnerStaticSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
