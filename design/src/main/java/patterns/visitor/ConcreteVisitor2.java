package patterns.visitor;

/**
 * Created by ziheng on 2019-09-25.
 */
public class ConcreteVisitor2 implements Visitor {
    @Override
    public void visit(Element element) {
        System.out.println("this is visitor B");
        element.operate();
    }
}
