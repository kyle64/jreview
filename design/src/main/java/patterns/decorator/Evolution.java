package patterns.decorator;

/**
 * Created by ziheng on 2019-09-05.
 */
public abstract class Evolution extends Pokemon {
    private Pokemon pokemon;

    public Evolution() {
    }

    public Evolution(String name, Integer level, Integer gender) {
        super(name, level, gender);
    }

    public Evolution(Pokemon pokemon) {
        this.pokemon = pokemon;

        this.gender = pokemon.getGender();
        this.level = pokemon.getLevel();
        this.item = pokemon.getItem();
    }

    String getOrigin() {
        return pokemon.getName();
    }

    abstract void showEvolutionInfo();
}
