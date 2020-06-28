package patterns.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ziheng on 2019-09-25.
 */
public class ConcreteAggregate implements Aggregate {
    private List list = new ArrayList();

    @Override
    public void add(Object object) {
        list.add(object);
    }

    @Override
    public void remove(Object object) {
        list.remove(object);
    }

    @Override
    public Iterator iterator() {
        return new ConcreteIterator(list);
    }
}
