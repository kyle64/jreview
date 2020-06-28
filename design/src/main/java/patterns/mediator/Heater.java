package patterns.mediator;

/**
 * Created by ziheng on 2019-09-24.
 */
// 吹暖风
public class Heater extends Device {
    @Override
    public void turnOn(Mediator mediator) {
        if (!this.isOpened()) {
            mediator.switchDevice();
            this.setOpened(true);
            System.out.println("开启吹风系统");
        }
    }

    @Override
    public void turnOff() {
        this.setOpened(false);
        System.out.println("关闭吹风系统");
    }
}
