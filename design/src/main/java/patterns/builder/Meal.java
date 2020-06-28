package patterns.builder;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ziheng on 2019-09-03.
 */
@Getter
@Setter
public class Meal {
    private String burger;
    private String snack;
    private String drink;
    private String dessert;

    @Override
    public String toString() {
        return "Meal{" +
                "burger='" + burger + '\'' +
                ", snack='" + snack + '\'' +
                ", drink='" + drink + '\'' +
                ", dessert='" + dessert + '\'' +
                '}';
    }
}
