package patterns.mediator;

/**
 * Created by ziheng on 2019-09-24.
 */
public abstract class Mediator {
    Device deviceA, deviceB;

    public Mediator(Device deviceA, Device deviceB) {
        this.deviceA = deviceA;
        this.deviceB = deviceB;
    }

    abstract void switchDevice();
}
