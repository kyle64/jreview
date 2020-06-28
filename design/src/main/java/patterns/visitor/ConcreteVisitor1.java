package patterns.visitor;

/**
 * Created by ziheng on 2019-09-25.
 */
public class ConcreteVisitor1 implements Visitor {
    @Override
    public void visit(Element element) {
        System.out.println("this is visitor A");
        element.operate();
    }
}
