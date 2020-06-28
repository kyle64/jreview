package patterns.singleton;

/**
 * Created by ziheng on 2019-08-30.
 */
public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
    }

    public static synchronized LazySingleton getInstance() { // 不加synchronized的话线程不安全
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
