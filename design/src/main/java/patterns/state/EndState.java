package patterns.state;

/**
 * Created by ziheng on 2019-09-19.
 */
public class EndState implements State {
    @Override
    public void handle(Context context) {
        System.out.println(Thread.currentThread().getName() + " end state: reached the end state");
        context.getPrepared().set(true);
    }
}
