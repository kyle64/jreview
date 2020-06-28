package patterns.state;

/**
 * Created by ziheng on 2019-09-19.
 */
public class StartState implements State {
    @Override
    public void handle(Context context) {
        System.out.println(Thread.currentThread().getName() + "start state: start working");
        context.setState(new WaitState());
        context.handle();
    }
}
