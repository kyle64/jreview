package patterns.visitor;

/**
 * Created by ziheng on 2019-09-25.
 */
public interface Element {
    void accept(Visitor visitor);
    void operate();
}
