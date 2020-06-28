package bean;

/**
 * Created by ziheng on 2019-08-01.
 */
public class ProfileBean {
    private Long id;
    private String name;
    private Integer age;

    public ProfileBean(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "id: " + id + " name: " + name + " age: " + age;
    }
}
