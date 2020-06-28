package algorithm;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by ziheng on 2020/6/15.
 */
public class MyLruCache<K, V> {
    class Node {
        K key;
        V value;
        int count;
        Node prev, next;

        public Node() {
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.count = 0;
        }

        @Override
        public String toString() {
            return "{" +
                    "key=" + key +
                    ", value=" + value +
                    ", count=" + count +
                    '}';
        }
    }

    final int maxSize;
    final Hashtable<K, Node> cache = new Hashtable<>();
    Node head, tail;

    public MyLruCache(int maxSize) {
        this.maxSize = maxSize;
    }

    public V get(K key) {
        if (!cache.containsKey(key)) {
            return null;
        } else {
            Node node = cache.get(key);
            // 移至双向链表队尾
            removeNode(node);
            linkToLast(node);
            return node.value;
        }
    }

    public void set(K key, V value) {
        if (!cache.containsKey(key)) {
            Node newNode = new Node(key, value);

            cache.put(key, newNode); // 加入到map中
            linkToLast(newNode); // 加入到双向链表的tail

            if (cache.size() > maxSize) {
                cache.remove(head.key);
                removeNode(head);
            }
        } else {
            Node node = cache.get(key);
            node.value = value;
            // 移至双向链表队尾
            removeNode(node);
            linkToLast(node);
        }
    }

    protected void linkToLast(Node node) {
        Node lastNode = tail;
        tail = node;
        if (lastNode == null) {
            head = node;
        } else {
            lastNode.next = node;
            node.prev = lastNode;
        }
    }

    protected Node removeNode(Node node) {
        Node prevNode = node.prev, nextNode = node.next;

        if (prevNode == null) {
            head = nextNode;
        } else {
            prevNode.next = nextNode;
        }

        if (nextNode == null) {
            tail = prevNode;
        } else {
            nextNode.prev = prevNode;
        }

        node.prev = null;
        node.next = null;

        return node;
    }

    public int size() {
        return cache.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = head;
        while (cur != null) {
            sb.append(cur.toString()).append(" ");
            cur = cur.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MyLruCache<Character, Integer> lru = new MyLruCache<>(15);

        String input = "fehwquinf329ik90d21uioqwnmcoiqmfewio3290jr3vnj908421f";
        for (int i = 0; i < input.length(); i++) {
            lru.set(input.charAt(i), i);
        }

        System.out.println("LRU中key为j的Entry的值为： " + lru.get('j'));
        System.out.println("LRU的大小 ：" + lru.size());
        System.out.println("LRU ：" + lru);
    }
}
