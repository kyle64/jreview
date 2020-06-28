package patterns.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ziheng on 2019-09-25.
 */
public class ConcreteIterator implements Iterator {
    private List list = new ArrayList<>();
    private int cursor = 0;

    public ConcreteIterator(List list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return cursor != list.size();
    }

    @Override
    public Object next() {
        Object object = null;
        if (this.hasNext()) {
            object = this.list.get(cursor++);
        }

        return object;
    }
}
