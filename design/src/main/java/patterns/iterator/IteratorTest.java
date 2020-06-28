package patterns.iterator;

import java.util.Iterator;

/**
 * Created by ziheng on 2019-09-25.
 */
public class IteratorTest {
    public static void main(String[] args) {
        Aggregate aggregate = new ConcreteAggregate();
        aggregate.add("小黄");
        aggregate.add("小红");
        aggregate.add("小黑");

        Iterator iterator = aggregate.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
