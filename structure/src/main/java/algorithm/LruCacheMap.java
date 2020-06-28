package algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ziheng on 2020/6/15.
 */
public class LruCacheMap<K, V> extends LinkedHashMap<K, V> implements Map<K, V> {
    private static final long serialVersionUID = 1L;
    private final int maxSize;

    public LruCacheMap(int maxSize) {
        super(16, 0.75f, true);
        this.maxSize = maxSize;
    }

    public LruCacheMap(int maxSize, int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Entry<K, V> eldest) {
        return size() > maxSize;
    }

    public static void main(String[] args) {
        Map<Character, Integer> lru = new LruCacheMap<>(10);
        String input = "fehwquinf329ik90d21uioqwnmcoiqmfewio3290jr3vnj908421f";
        for (int i = 0; i < input.length(); i++) {
            lru.put(input.charAt(i), i);
        }

        System.out.println("LRU中key为j的Entry的值为： " + lru.get('j'));
        System.out.println("LRU的大小 ：" + lru.size());
        System.out.println("LRU ：" + lru);
    }
}
