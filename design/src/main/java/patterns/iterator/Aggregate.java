package patterns.iterator;

import java.util.Iterator;

/**
 * Created by ziheng on 2019-09-25.
 */
public interface Aggregate {
    void add(Object object);
    void remove(Object object);
    Iterator iterator();
}
