package patterns.mediator;

/**
 * Created by ziheng on 2019-09-24.
 */
public class WindMediator extends Mediator {
    public WindMediator(Device deviceA, Device deviceB) {
        super(deviceA, deviceB);
    }

    @Override
    public void switchDevice() {
        if (deviceA.isOpened()) {
            deviceA.turnOff();
        } else if (deviceB.isOpened()) {
            deviceB.turnOff();
        }
    }
}
