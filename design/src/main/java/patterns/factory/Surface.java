package patterns.factory;

/**
 * Created by ziheng on 2019-09-02.
 */
public class Surface implements Tablet {
    @Override
    public void display() {
        System.out.println("this is Windows Surface");
    }
}
