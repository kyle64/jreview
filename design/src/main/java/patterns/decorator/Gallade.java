package patterns.decorator;

/**
 * Created by ziheng on 2019-09-06.
 */
public class Gallade extends Evolution {
    private static final String GALLADE_NAME = "Gallade";

    public Gallade() {
        super();
        this.name = GALLADE_NAME;
    }

    public Gallade(String name, Integer level, Integer gender) {
        super(name, level, gender);
    }

    public Gallade(Pokemon pokemon) {
        super(pokemon);
        this.name = GALLADE_NAME;
    }

    public Gallade(Integer level, Integer gender) {
        this(GALLADE_NAME, level, gender);
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
        return false;
    }

    @Override
    public Pokemon evolve() {
        return null;
    }
}
