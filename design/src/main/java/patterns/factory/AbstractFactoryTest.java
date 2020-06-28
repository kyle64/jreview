package patterns.factory;

/**
 * Created by ziheng on 2019-09-02.
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        Factory appleFactory = new AppleFactory();
        Phone phone1 = appleFactory.producePhone();
        Tablet tablet1 = appleFactory.produceTablet();

        Factory windowsFactory = new WindowsFactory();
        Phone phone2 = windowsFactory.producePhone();
        Tablet tablet2 = windowsFactory.produceTablet();

        Factory samsungFactory = new SamsungFactory();
        Phone phone3 = samsungFactory.producePhone();

        phone1.display();
        phone2.display();
        phone3.display();

        tablet1.display();
        tablet2.display();
    }
}
