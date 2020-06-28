package patterns.state;

import java.util.Random;

/**
 * Created by ziheng on 2019-09-19.
 */
public class ProcessState implements State {
    @Override
    public void handle(Context context) {
        System.out.println(Thread.currentThread().getName() + " process state: processing");

        int input =  new Random().nextInt(10);
        System.out.println(Thread.currentThread().getName() + " process state: input is " + input);

        if (input > 5) {
            System.out.println(Thread.currentThread().getName() + " process state: success");
            context.setState(new EndState());
        } else {
            System.out.println(Thread.currentThread().getName() + " process state: failed, back to last state");
            context.getPrepared().set(true);
            context.setState(new WaitState());
        }

        context.handle();
    }
}
