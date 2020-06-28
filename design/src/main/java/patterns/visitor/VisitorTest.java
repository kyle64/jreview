package patterns.visitor;

/**
 * Created by ziheng on 2019-09-26.
 */
public class VisitorTest {
    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();
        Visitor visitorA = new ConcreteVisitor1();
        Visitor visitorB = new ConcreteVisitor2();

        objectStructure.accept(visitorA);
        System.out.println("-----------------------");
        objectStructure.accept(visitorB);
    }
}
