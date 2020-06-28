package concurrent;

/**
 * Created by ziheng on 2019-08-14.
 */
public class ClassReordering {
    int x = 0, y = 0;

    public void writer() {
        x = 1;
        y = 2;
    }

    public void reader() {
        int r1 = y;
        int r2 = x;
    }
}
