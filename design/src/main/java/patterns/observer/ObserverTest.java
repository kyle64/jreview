package patterns.observer;

/**
 * Created by ziheng on 2019-09-24.
 */
public class ObserverTest {
    public static void main(String[] args) {
        TargetSubject whiteWine = new WhiteWineSubject();
        TargetSubject tech = new TechSubject();

        Observer fundManager1 = new FundManager1();
        Observer fundManager2 = new FundManager2();

        whiteWine.addObserver(fundManager1);
        whiteWine.addObserver(fundManager2);

        tech.addObserver(fundManager1);
        tech.addObserver(fundManager2);

        whiteWine.notifyObservers(0.02);
        tech.notifyObservers(-0.03);
    }
}
