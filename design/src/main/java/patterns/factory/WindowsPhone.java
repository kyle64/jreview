package patterns.factory;

/**
 * Created by ziheng on 2019-09-02.
 */
public class WindowsPhone implements Phone {
    @Override
    public void display() {
        System.out.println("this is Windows Phone");
    }
}
