package concurrent;

import java.util.Random;

/**
 * Created by ziheng on 2019-08-16.
 */
public class MyContextHolder {
    private static ThreadLocal<Integer> contextHolder = new ThreadLocal<>();

    public static Integer getCount() {
        return contextHolder.get();
    }

    public static void setCount(Integer value) {
        contextHolder.set(value);
    }

    public static void clear() {
        contextHolder.remove();
    }

}
