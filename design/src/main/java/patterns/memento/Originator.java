package patterns.memento;

import lombok.Getter;

/**
 * Created by ziheng on 2019-09-26.
 */
@Getter
public class Originator {
    private String state;

    public Memento saveState() {
        System.out.println("存档，当前进度： " + this.state);
        return new Memento(state);
    }

    public void loadState(Memento memento) {
        this.state = memento.getState();
        System.out.println("读档，当前进度： " + this.state);
    }

    public void setState(String state) {
        this.state = state;
        System.out.println("当前进度： " + this.state);
    }
}
