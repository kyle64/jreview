package patterns.builder;

/**
 * Created by ziheng on 2019-09-03.
 */
public class McDonaldMealDirector {
    private MealBuilder builder;

    public McDonaldMealDirector(MealBuilder builder) {
        this.builder = builder;
    }

    public Meal construct() {
        builder.buildBurger();
        builder.buildSnack();
        builder.buildDrink();
        builder.buildDessert();

        return builder.build();
    }
}
