package car;

/**
 * Created by ziheng on 2019-08-05.
 */
public class Car {
    private String make; // 品牌
    private String model; // 车型
    private Integer age; // 车龄

    public Car() {
    }

    public Car(String make, String model, Integer age) {
        this.make = make;
        this.model = model;
        this.age = age;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
