package patterns.decorator;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ziheng on 2019-09-05.
 */
@Getter
@Setter
public abstract class Pokemon implements Evolvable {
    public static final int MALE = 1;
    public static final int FEMALE = 0;

    public Pokemon() {
    }

    public Pokemon(String name, Integer level, Integer gender) {
        this.name = name;
        this.level = level;
        this.gender = gender;
    }

    protected String name;
    protected Integer level;
    protected Integer gender;
    protected String item;

    abstract void display();

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", gender=" + gender +
                ", item='" + item + '\'' +
                '}';
    }
}
