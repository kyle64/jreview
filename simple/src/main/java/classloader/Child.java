package classloader;

/**
 * Created by ziheng on 2019-10-08.
 */
public class Child extends Parent {

    static {
        new Print("a");
    }

    public static Print obj1 = new Print("b");

    public Print obj2 = new Print("c");

    public Child() {
        new Print("d");
    }

    public static Print obj3 = new Print("e");

    public Print obj4 = new Print("f");

    public static void main(String[] args) {
        Parent obj1 = new Child();
        Parent obj2 = new Child();
    }
}
