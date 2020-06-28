package patterns.proxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ziheng on 2019-08-07.
 */
public class DealerB implements Dealer {
    private static final String[] cars = {"Ferrari", "Porsche", "Lamborghini"};
    private List<String> carList = new ArrayList<>();

    public DealerB() {
        this.carList = Arrays.asList(cars);
    }

    @Override
    public int size() {
        return carList.size();
    }

    @Override
    public void display() {
        StringBuilder sb = new StringBuilder();
        for (String s : carList) {
            sb.append(s).append(", ");
        }

        System.out.println(sb.delete(sb.lastIndexOf(","), sb.length()));
    }
}
