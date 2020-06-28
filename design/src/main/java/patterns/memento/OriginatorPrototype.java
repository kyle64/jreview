package patterns.memento;

/**
 * Created by ziheng on 2019-09-26.
 */
public class OriginatorPrototype implements Cloneable {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        System.out.println("当前进度： " + this.state);
    }

    public OriginatorPrototype saveState() {
        return this.clone();
    }

    public void loadState(OriginatorPrototype originatorPrototype) {
        this.setState(originatorPrototype.getState());
    }

    @Override
    protected OriginatorPrototype clone() {
        OriginatorPrototype prototype = null;

        try {
            prototype = (OriginatorPrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return prototype;
    }
}
