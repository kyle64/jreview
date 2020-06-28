package patterns.command;

/**
 * Created by ziheng on 2019-09-18.
 */

// 命令接受者Receiver
public class TV {
    private int channel;

    public void turnOn() {
        System.out.println("turn on television");
    }

    public void turnOff() {
        System.out.println("turn off television");
    }

    public void setChannel(int channel) {
        this.channel = channel;
        System.out.println("current channel is " + this.channel);
    }
}
