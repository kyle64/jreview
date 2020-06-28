package patterns.state;

/**
 * Created by ziheng on 2019-09-19.
 */
public class WaitState implements State {
    @Override
    public void handle(Context context) {
        if (context.getPrepared().getAndSet(false)) {
            context.setState(new ProcessState());
            System.out.println(Thread.currentThread().getName() + " wait state: ready to move on");
        } else {
            System.out.println(Thread.currentThread().getName() + " wait state: still waiting");
        }
        context.handle();
    }
}
