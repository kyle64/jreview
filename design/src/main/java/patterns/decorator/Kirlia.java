package patterns.decorator;

/**
 * Created by ziheng on 2019-09-05.
 */
public class Kirlia extends Evolution {
    private static final String KIRLIA_NAME = "Kirlia";
    private static final int KIRLIA_EVOLVE_LEVEL = 30;
    private static final String KIRLIA_EVOLVE_ITEM = "Dawn Stone";

    public Kirlia() {
        super();
        this.name = KIRLIA_NAME;
    }

    public Kirlia(Integer level, Integer gender) {
        super(KIRLIA_NAME, level, gender);
    }

    public Kirlia(Pokemon pokemon) {
        super(pokemon);
        this.name = KIRLIA_NAME;
    }

    @Override
    void showEvolutionInfo() {
        System.out.println(this.name + " is evolved from " + getOrigin());
    }

    @Override
    void display() {
        System.out.println(this);
    }

    @Override
    public boolean isEvolutionAvailable() {
        return checkGardevoirEvolution() || checkGalladeEvolution();
    }

    @Override
    public Pokemon evolve() {
        if (checkGalladeEvolution()) {
            System.out.println(this.name + " evolved to Gallade");
            this.setItem(null);
            return new Gallade(this);
        } else if (checkGardevoirEvolution()) {
            System.out.println(this.name + " evolved to Gardevoir");
            return new Gardevoir(this);
        } else {
            System.out.println("evolution failed");
            return this;
        }
    }

    private boolean checkGardevoirEvolution() {
        return this.level >= KIRLIA_EVOLVE_LEVEL;
    }

    private boolean checkGalladeEvolution() {
        return KIRLIA_EVOLVE_ITEM.equalsIgnoreCase(this.item) && this.gender.equals(Pokemon.MALE);
    }

    @Override
    public String toString() {
        return "Kirlia{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", gender=" + gender +
                ", item='" + item + '\'' +
                '}';
    }
}
