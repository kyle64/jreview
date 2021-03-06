package patterns.observer;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by ziheng on 2019-09-20.
 */
public class WhiteWineSubject implements TargetSubject {
    private List<Observer> observerList = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observerList.size() > 0) {
            observerList.remove(observer);
        }
    }

    @Override
    public void notifyObservers(double amount) {
        for (Observer observer : observerList) {
            observer.operate("白酒", amount);
        }
    }

}
