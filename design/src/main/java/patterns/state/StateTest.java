package patterns.state;

/**
 * Created by ziheng on 2019-09-19.
 */
public class StateTest {
    public static void main(String[] args) {
        Context context = new Context();

        context.setState(new StartState());

        for (int i = 0; i < 10; i++) {
            new Thread(new StateThread(context)).start();
        }

//        context.handle();
    }
}

class StateThread implements Runnable {
    private Context context;

    public StateThread(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        context.handle();
    }
}