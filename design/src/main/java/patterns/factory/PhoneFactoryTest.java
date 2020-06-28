package patterns.factory;

/**
 * Created by ziheng on 2019-09-02.
 */
public class PhoneFactoryTest {
    public static void main(String[] args) {
        PhoneFactory iPhoneFactory = new IPhoneFactory();
        Phone phone1 = iPhoneFactory.producePhone();

        PhoneFactory samsungPhoneFactory = new SamsungPhoneFactory();
        Phone phone2 = samsungPhoneFactory.producePhone();

        phone1.display();
        phone2.display();
    }
}
