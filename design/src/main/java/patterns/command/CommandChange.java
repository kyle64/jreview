package patterns.command;

/**
 * Created by ziheng on 2019-09-18.
 */
public class CommandChange implements Command {
    private TV tv;
    private int targetChannel;

    public CommandChange(TV tv, int targetChannel) {
        this.tv = tv;
        this.targetChannel = targetChannel;
    }

    @Override
    public void execute() {
        tv.setChannel(targetChannel);
    }
}
