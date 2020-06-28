package patterns.factory;

/**
 * Created by ziheng on 2019-09-02.
 */
public class PhoneSimpleFactory {
    public static Phone producePhone(String type) {
        switch (type) {
            case "apple":
                return new IPhone();
            case "samsung":
                return new SamsungPhone();
            default:
                break;
        }
        return null;
    }

    public static void main(String[] args) {
        Phone phone1 = PhoneSimpleFactory.producePhone("apple");
        Phone phone2 = PhoneSimpleFactory.producePhone("samsung");

        phone1.display();
        phone2.display();
    }
}
