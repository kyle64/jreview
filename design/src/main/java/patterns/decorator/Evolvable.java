package patterns.decorator;

/**
 * Created by ziheng on 2019-09-05.
 */
public interface Evolvable {
    boolean isEvolutionAvailable();
    Pokemon evolve();
}
