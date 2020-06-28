package patterns.proxy;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ziheng on 2019-08-07.
 */
public class DealerA implements Dealer {
    private static final String[] cars = {"BMW 730", "Benz E300", "Audi A7"};
    private List<String> carList = new ArrayList<>();

    public DealerA() {
        this.carList = Arrays.asList(cars);
    }

    public DealerA(List<String> carList) {
        this.carList = carList;
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
