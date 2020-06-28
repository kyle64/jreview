package patterns.builder;

/**
 * Created by ziheng on 2019-09-03.
 */
public class McDonaldMealBuilder extends MealBuilder {
    private Meal meal = new Meal();

    @Override
    void buildBurger() {
        meal.setBurger("Big Mic");
    }

    @Override
    void buildSnack() {
        meal.setBurger("Chicken Wings");
    }

    @Override
    void buildDrink() {
        meal.setDrink("Ice Coke");
    }

    @Override
    void buildDessert() {
        meal.setDessert("McFlury");
    }

    @Override
    Meal build() {
        return meal;
    }
}
