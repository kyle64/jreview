package patterns.command;

/**
 * Created by ziheng on 2019-09-18.
 */
//调用者Invoker遥控器
public class RemoteControl {
    private Command onCommand, offCommand, changeCommand;

    public RemoteControl(Command onCommand, Command offCommand, Command changeCommand) {
        this.onCommand = onCommand;
        this.offCommand = offCommand;
        this.changeCommand = changeCommand;
    }

    public void turnOn() {
        onCommand.execute();
    }

    public void turnOff() {
        offCommand.execute();
    }

    public void changeChannel() {
        changeCommand.execute();
    }
}
