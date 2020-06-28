package patterns.memento;

/**
 * Created by ziheng on 2019-09-27.
 */
public class PrototypeCaretaker {
    private OriginatorPrototype opt;

    public void setMemento(OriginatorPrototype opt) {
        this.opt = opt;
    }

    public OriginatorPrototype getMemento() {
        return opt;
    }
}
