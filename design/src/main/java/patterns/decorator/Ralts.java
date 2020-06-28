package patterns.decorator;

/**
 * Created by ziheng on 2019-09-05.
 */
public class Ralts extends Pokemon {
    private static final String RALTS_NAME = "Ralts";
    private static final int RALTS_EVOLVE_LEVEL = 20;

    public Ralts() {
        super();
        this.name = RALTS_NAME;
    }

    public Ralts(Integer level, Integer gender) {
        super(RALTS_NAME, level, gender);
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public boolean isEvolutionAvailable() {
        return level >= RALTS_EVOLVE_LEVEL;
    }

    @Override
    public Pokemon evolve() {
        if (isEvolutionAvailable()) {
            System.out.println(this.name + " evolved to Kirlia");
            return new Kirlia(this);
        } else {
            System.out.println("evolution failed");
            return this;
        }
    }

    @Override
    public String toString() {
        return "Ralts{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", gender=" + gender +
                '}';
    }
}
