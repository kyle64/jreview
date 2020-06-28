package patterns.observer;

/**
 * Created by ziheng on 2019-09-20.
 */
public interface TargetSubject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(double amount);
}
