package patterns.command;

/**
 * Created by ziheng on 2019-09-18.
 */
public class CommandOn implements Command {
    private TV tv;

    public CommandOn(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOn();
    }
}
