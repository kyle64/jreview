package patterns.mediator;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ziheng on 2019-09-24.
 */
@Getter
@Setter
public abstract class Device {
    protected boolean opened;

    abstract void turnOn(Mediator mediator);
    abstract void turnOff();
}
