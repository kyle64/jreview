package patterns.mediator;

/**
 * Created by ziheng on 2019-09-24.
 */
public class MediatorTest {
    public static void main(String[] args) {
        Device ventilation = new Ventilation();
        Device heater = new Heater();

        Mediator mediator = new WindMediator(ventilation, heater);
        ventilation.setOpened(true);

        heater.turnOn(mediator);
        ventilation.turnOn(mediator);
    }
}
