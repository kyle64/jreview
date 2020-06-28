package patterns.adaptor;

/**
 * Created by ziheng on 2019-09-03.
 */
public class ObjectAdapter implements Target {
    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.adapteeRequest();
    }

    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target targetAdapter = new ObjectAdapter(adaptee);
        targetAdapter.request();
    }
}
