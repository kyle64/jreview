package patterns.memento;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by ziheng on 2019-09-26.
 */
public class CareTaker {
    private List<Memento> mementos = new ArrayList<>();

    public int add(Memento memento) {
        mementos.add(memento);
        return mementos.indexOf(memento);
    }

    public void remove(Memento memento) {
        mementos.remove(memento);
    }

    public Memento get(int i) {
        return this.mementos.get(i);
    }

    public void display() {
        for (Memento memento : mementos) {
            System.out.println(memento.getState());
        }
    }
}
