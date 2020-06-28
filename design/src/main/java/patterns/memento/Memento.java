package patterns.memento;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ziheng on 2019-09-26.
 */
@Getter
@Setter
public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

}
