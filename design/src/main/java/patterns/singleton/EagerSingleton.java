package patterns.singleton;

/**
 * Created by ziheng on 2019-08-30.
 */
public class EagerSingleton {
    private static EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}
