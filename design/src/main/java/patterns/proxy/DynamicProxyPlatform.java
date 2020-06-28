package patterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by ziheng on 2019-08-07.
 */
public class DynamicProxyPlatform implements InvocationHandler {
    private Dealer dealer;

    public Dealer getProxyInstance(Dealer dealer) {
        this.dealer = dealer;
        return (Dealer) Proxy.newProxyInstance(dealer.getClass().getClassLoader(), dealer.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("searching dealer's cars ...");

        Object object = method.invoke(dealer, args);

        System.out.println(String.format("all cars from %s is displayed", dealer.getClass().getSimpleName()));
        return object;
    }
}
