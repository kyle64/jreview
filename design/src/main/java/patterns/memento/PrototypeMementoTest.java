package patterns.memento;

/**
 * Created by ziheng on 2019-09-27.
 */
public class PrototypeMementoTest {
    public static void main(String[] args) {
        OriginatorPrototype originatorPrototype = new OriginatorPrototype();
        PrototypeCaretaker prototypeCaretaker = new PrototypeCaretaker();

        originatorPrototype.setState("S0");
        prototypeCaretaker.setMemento(originatorPrototype.saveState()); // 保存
        originatorPrototype.setState("S1");
        originatorPrototype.loadState(prototypeCaretaker.getMemento()); // 读取

    }
}
