package patterns.mediator;

/**
 * Created by ziheng on 2019-09-24.
 */
// 换气
public class Ventilation extends Device {

    @Override
    public void turnOn(Mediator mediator) {
        if (!this.isOpened()) {
            mediator.switchDevice();
            this.setOpened(true);
            System.out.println("开启换气系统");
        }
    }

    @Override
    public void turnOff() {
        this.setOpened(false);
        System.out.println("关闭换气系统");
    }
}
