package patterns.builder;

/**
 * Created by ziheng on 2019-09-03.
 */
public class BuilderTest {
    public static void main(String[] args) {
        McDonaldMealDirector mealDirector = new McDonaldMealDirector(new McDonaldMealBuilder());
        Meal meal = mealDirector.construct();
        System.out.println(meal);

        ComputerBuilder myComputerBuilder = new MyComputerBuilder();
        Computer computer = myComputerBuilder.buildScreen(15)
                .buildGraphics("1080 Ti")
                .buildMemory("8G DDR")
                .buildProcessor("2.4GHz Core i7")
                .buildStorage("512G SSD")
                .build();
        System.out.println(computer);
    }
}
