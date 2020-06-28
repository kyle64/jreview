package patterns.flyweight;

/**
 * Created by ziheng on 2019-09-09.
 */
public class UnsharedChessCount implements ChessFlyweight<Integer> {
    private int count = 0;

    @Override
    public void operate(Integer param) {
        System.out.println("current chess count is " + param);
    }
}
