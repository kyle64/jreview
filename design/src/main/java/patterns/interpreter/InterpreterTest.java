package patterns.interpreter;

/**
 * Created by ziheng on 2019-09-26.
 */
public class InterpreterTest {
    public static void main(String[] args) {
        Expression rule1 = new OrExpression(new TerminalExpression("QQ"), new TerminalExpression("腾讯"));
        Expression rule2 = new AndExpression(new TerminalExpression("淘"), new TerminalExpression("宝"));

        System.out.println(rule1.interpret("QQ飞车")); //true
        System.out.println(rule1.interpret("腾讯视频")); //true
        System.out.println(rule2.interpret("淘宝")); //true
        System.out.println(rule2.interpret("支付宝")); //false

    }
}
