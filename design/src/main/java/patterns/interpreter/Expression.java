package patterns.interpreter;

/**
 * Created by ziheng on 2019-09-26.
 */
public interface Expression {
    boolean interpret(String context);
}
