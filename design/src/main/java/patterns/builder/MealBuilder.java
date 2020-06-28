package patterns.builder;

/**
 * Created by ziheng on 2019-09-03.
 */
public abstract class MealBuilder {
    abstract void buildBurger(); //主食
    abstract void buildSnack(); //小食
    abstract void buildDrink(); //饮料
    abstract void buildDessert(); //甜点

    abstract Meal build();
}
