package patterns.adaptor;

/**
 * Created by ziheng on 2019-09-03.
 */
public class ClassAdapter extends Adaptee implements Target {
    @Override
    public void request() {
        super.adapteeRequest();
    }

    public static void main(String[] args) {
        Target adapterTarget = new ClassAdapter();
        adapterTarget.request();
    }
}
