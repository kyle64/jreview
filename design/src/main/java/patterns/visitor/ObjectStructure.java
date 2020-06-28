package patterns.visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by ziheng on 2019-09-25.
 */
public class ObjectStructure {
    private List<Element> elements = new ArrayList<>();

    public ObjectStructure() {
        getList();
    }

    private void getList() {
        Random ran = new Random();
        for (int i = 0; i < 10; i++) {
            int a = ran.nextInt(100);
            if (a > 50) {
                elements.add(new ConcreteElement1());
            } else {
                elements.add(new ConcreteElement2());
            }
        }
    }

    public void accept(Visitor visitor) {
        Iterator<Element> iterator = this.elements.iterator();
        while (iterator.hasNext()) {
            iterator.next().accept(visitor);
        }
    }
}
