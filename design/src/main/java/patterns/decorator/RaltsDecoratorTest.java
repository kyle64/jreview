package patterns.decorator;

/**
 * Created by ziheng on 2019-09-06.
 */
public class RaltsDecoratorTest {
    public static void main(String[] args) {
        Ralts ralts = new Ralts(5, Pokemon.MALE);
        ralts.display();
        ralts.setItem("Dawn Stone");
        ralts.setLevel(21);

        // ralts to kirlia
        Pokemon p1 = ralts.evolve();
        p1.display();

        // ralts to gallade
        p1.setLevel(30);
        Evolution p2 = (Evolution) p1.evolve();
        p2.display();
        p2.showEvolutionInfo();

        // decorator ralts to gardevoir
        Pokemon p3 = new Gardevoir(new Kirlia(new Ralts(33, Pokemon.MALE)));
        p3.display();
    }
}
