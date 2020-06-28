package concurrent;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ziheng on 2019-08-23.
 */
public class MyMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>(2);
        for (int i = 0; i < 2000; i++) {
            map.put(String.valueOf(i), new Random().nextInt());
        }
    }
}
