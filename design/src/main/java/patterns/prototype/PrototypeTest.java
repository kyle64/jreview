package patterns.prototype;

import java.util.Random;

/**
 * Created by ziheng on 2019-09-02.
 */
public class PrototypeTest {
    public static void main(String[] args) throws Exception {
        Account account = new Account("admin", 18, 100);

        Customer customer1 = new Customer(new Random().nextLong(), account);
        Customer customer2 = customer1.clone();
        Customer customer3 = customer1.deepClone();
        Customer customer4 = Customer.deepClone(customer1);

        System.out.println(customer1 == customer2);
        System.out.println(customer1.getAccount().getAge() == customer2.getAccount().getAge());
        System.out.println(customer1.getAccount().getAge() == customer3.getAccount().getAge());
        System.out.println(customer3.getAccount().getAge() == customer4.getAccount().getAge());

    }
}
