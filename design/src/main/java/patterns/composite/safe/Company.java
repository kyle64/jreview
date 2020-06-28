package patterns.composite.safe;

/**
 * Created by ziheng on 2019-09-12.
 */
public abstract class Company {
    protected String name;

    public Company(String name) {
        this.name = name;
    }
    public abstract void display(int depth);
}