package patterns.command;

/**
 * Created by ziheng on 2019-09-18.
 */
public class CommandTest {
    public static void main(String[] args) {
        TV myTv = new TV();
        Command on = new CommandOn(myTv);
        CommandOff off = new CommandOff(myTv);
        CommandChange change2 = new CommandChange(myTv, 7);

        RemoteControl control = new RemoteControl(on, off, change2);

        control.turnOn();
        control.changeChannel();
        control.turnOff();
    }
}
