package algorithm;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by ziheng on 2020/6/16.
 */
public class MyLruKCache<K, V> extends MyLruCache<K, V> {
    private Map<K, Node> historyCache = new Hashtable<>();
    private int cacheLimit;

    public MyLruKCache(int maxSize, int k) {
        super(maxSize);
        this.cacheLimit = k;
    }

    @Override
    public V get(K key) {
        return super.get(key);
    }

    @Override
    public void set(K key, V value) {
        if (!cache.containsKey(key)) {
            // 检查数据访问历史
            Node newNode = historyCache.get(key);
            if (newNode == null) {
                newNode = new Node(key, value);
                historyCache.put(key, newNode);
            }
            newNode.count++;

            if (newNode.count >= cacheLimit) {
                cache.put(key, historyCache.remove(key)); // 加入到lru map中
                linkToLast(newNode); // 加入到双向链表的tail
            }

//            cache.put(key, newNode); // 加入到map中
//            linkToLast(newNode); // 加入到双向链表的tail

            if (cache.size() > maxSize) {
                cache.remove(head.key);
                removeNode(head);
            }
        } else {
            Node node = cache.get(key);
            node.value = value;
            node.count++;
            // 移至双向链表队尾
            removeNode(node);
            linkToLast(node);
        }
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String[] args) {
        MyLruKCache<Character, Integer> lru = new MyLruKCache<>(15, 2);

        String input = "fehwquinf329ik90d21uioqwnmcoiqmfewio3290jr3vnj908421f";
        for (int i = 0; i < input.length(); i++) {
            lru.set(input.charAt(i), i);
        }

        System.out.println("LRU中key为j的Entry的值为： " + lru.get('u'));
        System.out.println("LRU的大小 ：" + lru.size());
        System.out.println("LRU ：" + lru);
    }
}
