package patterns.visitor;

/**
 * Created by ziheng on 2019-09-25.
 */
public class ConcreteElement2 implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void operate() {
        System.out.println("element2 operation");
    }
}
