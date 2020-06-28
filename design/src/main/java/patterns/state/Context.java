package patterns.state;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by ziheng on 2019-09-19.
 */
@Getter
@Setter
public class Context {
    private State state;

    private AtomicBoolean prepared = new AtomicBoolean(true);

    public void handle() {
        state.handle(this);
    }
}
