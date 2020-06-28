package patterns.singleton;

/**
 * Created by ziheng on 2019-08-30.
 */
public class DoubleCheckSingleton {
    private static volatile DoubleCheckSingleton instance; // volatile线程可见，同时避免重排

    private DoubleCheckSingleton() {
    }

    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
