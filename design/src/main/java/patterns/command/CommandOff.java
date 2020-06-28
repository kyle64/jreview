package patterns.command;

/**
 * Created by ziheng on 2019-09-18.
 */
public class CommandOff implements Command {
    private TV tv;

    public CommandOff(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOff();
    }
}
