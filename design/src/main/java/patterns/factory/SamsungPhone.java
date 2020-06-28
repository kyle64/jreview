package patterns.factory;

/**
 * Created by ziheng on 2019-09-02.
 */
public class SamsungPhone implements Phone {
    @Override
    public void display() {
        System.out.println("this is samsung note phone");
    }
}
