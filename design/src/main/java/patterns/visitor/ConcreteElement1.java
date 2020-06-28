package patterns.visitor;

/**
 * Created by ziheng on 2019-09-25.
 */
public class ConcreteElement1 implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void operate() {
        System.out.println("element1 operation");
    }
}
