package algorithm;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

/**
 * Created by ziheng on 2020/7/28.
 * 代码参考：https://www.jianshu.com/p/2a579cb06c58
 */
class ConsistentHash<T> {

    /**
     * 哈希函数
     */
    private final HashFunction hashFunction;

    /**
     * 虚拟节点数 ， 越大分布越均衡，但越大，在初始化和变更的时候效率差一点。 测试中，设置200基本就均衡了。
     */
    private final int numberOfReplicas;

    /**
     * 环形Hash空间
     */
    private final SortedMap<Integer, T> circle = new TreeMap<Integer, T>();

    /**
     * @param hashFunction
     *            ，哈希函数
     * @param numberOfReplicas
     *            ，虚拟服务器系数
     * @param nodes
     *            ，服务器节点
     */
    public ConsistentHash(HashFunction hashFunction, int numberOfReplicas,
                          Collection<T> nodes) {
        this.hashFunction = hashFunction;
        this.numberOfReplicas = numberOfReplicas;

        for (T node : nodes) {
            this.addNode(node);
        }
    }

    /**
     * 添加物理节点，每个node 会产生numberOfReplicas个虚拟节点，这些虚拟节点对应的实际节点是node
     */
    public void addNode(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            int hashValue = hashFunction.hash(node.toString() + i);
            circle.put(hashValue, node);
        }
    }

    /**移除物理节点，将node产生的numberOfReplicas个虚拟节点全部移除
     * @param node
     */
    public void removeNode(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            int hashValue = hashFunction.hash(node.toString() + i);
            circle.remove(hashValue);
        }
    }

    /**
     * 得到映射的物理节点
     *
     * @param key
     * @return
     */
    public T getNode(Object key) {
        if (circle.isEmpty()) {
            return null;
        }
        int hashValue = hashFunction.hash(key);
//      System.out.println("key---" + key + " : hash---" + hash);
        if (!circle.containsKey(hashValue)) {
            // 返回键大于或等于hash的node，即沿环的顺时针找到一个虚拟节点
            SortedMap<Integer, T> tailMap = circle.tailMap(hashValue);
            // System.out.println(tailMap);
            // System.out.println(circle.firstKey());
            hashValue = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
//      System.out.println("hash---: " + hash);
        return circle.get(hashValue);
    }

    static class HashFunction {
        /**
         * MurMurHash算法，是非加密HASH算法，性能很高，
         * 比传统的CRC32,MD5，SHA-1（这两个算法都是加密HASH算法，复杂度本身就很高，带来的性能上的损害也不可避免）
         * 等HASH算法要快很多，而且据说这个算法的碰撞率很低. http://murmurhash.googlepages.com/
         */
        int hash(Object key) {
            ByteBuffer buf = ByteBuffer.wrap(key.toString().getBytes());
            int seed = 0x1234ABCD;

            ByteOrder byteOrder = buf.order();
            buf.order(ByteOrder.LITTLE_ENDIAN);

            long m = 0xc6a4a7935bd1e995L;
            int r = 47;

            long h = seed ^ (buf.remaining() * m);

            long k;
            while (buf.remaining() >= 8) {
                k = buf.getLong();

                k *= m;
                k ^= k >>> r;
                k *= m;

                h ^= k;
                h *= m;
            }

            if (buf.remaining() > 0) {
                ByteBuffer finish = ByteBuffer.allocate(8).order(
                        ByteOrder.LITTLE_ENDIAN);
                finish.put(buf).rewind();
                h ^= finish.getLong();
                h *= m;
            }

            h ^= h >>> r;
            h *= m;
            h ^= h >>> r;
            buf.order(byteOrder);
            return (int) h;
        }
    }
}

class Test {

    public static void main(String[] args) {
        HashSet<String> serverNode = new HashSet<String>();
        serverNode.add("127.1.1.1#A");
        serverNode.add("127.2.2.2#B");
        serverNode.add("127.3.3.3#C");
        serverNode.add("127.4.4.4#D");

        Map<String, Integer> serverNodeMap = new HashMap<String, Integer>();

        ConsistentHash<String> consistentHash = new ConsistentHash<String>(
                new ConsistentHash.HashFunction(), 200, serverNode);

        int count = 50000;

        for (int i = 0; i < count; i++) {
            String serverNodeName = consistentHash.getNode(i);
            // System.out.println(i + " 映射到物理节点---" + serverNodeName);
            if (serverNodeMap.containsKey(serverNodeName)) {
                serverNodeMap.put(serverNodeName,
                        serverNodeMap.get(serverNodeName) + 1);
            } else {
                serverNodeMap.put(serverNodeName, 1);
            }
        }
        // System.out.println(serverNodeMap);

        showServer(serverNodeMap);
        serverNodeMap.clear();

        consistentHash.removeNode("127.1.1.1#A");
        System.out.println("-------------------- remove 127.1.1.1#A");

        for (int i = 0; i < count; i++) {
            String serverNodeName = consistentHash.getNode(i);
            // System.out.println(i + " 映射到物理节点---" + serverNodeName);
            if (serverNodeMap.containsKey(serverNodeName)) {
                serverNodeMap.put(serverNodeName,
                        serverNodeMap.get(serverNodeName) + 1);
            } else {
                serverNodeMap.put(serverNodeName, 1);
            }
        }

        showServer(serverNodeMap);
        serverNodeMap.clear();

        consistentHash.addNode("127.5.5.5#E");
        System.out.println("-------------------- add 127.5.5.5#E");

        for (int i = 0; i < count; i++) {
            String serverNodeName = consistentHash.getNode(i);
            // System.out.println(i + " 映射到物理节点---" + serverNodeName);
            if (serverNodeMap.containsKey(serverNodeName)) {
                serverNodeMap.put(serverNodeName,
                        serverNodeMap.get(serverNodeName) + 1);
            } else {
                serverNodeMap.put(serverNodeName, 1);
            }
        }

        showServer(serverNodeMap);
        serverNodeMap.clear();

        consistentHash.addNode("127.6.6.6#F");
        System.out.println("-------------------- add 127.6.6.6#F");
        count *= 2;
        System.out.println("-------------------- 业务量加倍");
        for (int i = 0; i < count; i++) {
            String serverNodeName = consistentHash.getNode(i);
            // System.out.println(i + " 映射到物理节点---" + serverNodeName);
            if (serverNodeMap.containsKey(serverNodeName)) {
                serverNodeMap.put(serverNodeName,
                        serverNodeMap.get(serverNodeName) + 1);
            } else {
                serverNodeMap.put(serverNodeName, 1);
            }
        }
        showServer(serverNodeMap);

    }

    /**
     * 服务器运行状态
     *
     * @param map
     */
    public static void showServer(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> m : map.entrySet()) {
            System.out.println(m.getKey() + ", 存储数据量 " + m.getValue());
        }
    }

}
