package patterns.composite.transparent;

/**
 * Created by ziheng on 2019-09-11.
 */
public abstract class Company {
    protected String name;

    public Company(String name) {
        this.name = name;
    }

    public abstract void add(Company company);
    public abstract void remove(Company company);
    public abstract void display(int depth);
}
