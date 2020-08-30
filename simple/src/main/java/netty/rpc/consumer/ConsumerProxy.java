package netty.rpc.consumer;

import netty.rpc.Request;
import netty.rpc.Response;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;


/**
 * Created by ziheng on 2020/8/28.
 */
public class ConsumerProxy<T> implements InvocationHandler {
    private static Class clazz;

    public static Object create(Class target) {
        clazz = target;
        return Proxy.newProxyInstance(target.getClassLoader(), new Class[]{target}, new ConsumerProxy());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setId(UUID.randomUUID().toString());
        request.setClassName(clazz.getSimpleName());
        request.setMethodName(method.getName());
        request.setParameters(args);
        request.setParameterTypes(method.getParameterTypes());

        ConsumerClient client = new ConsumerClient("127.0.0.1", 8379);
        client.start();
        Response response = client.send(request);
        client.shutdown();
        return response.getContent();
    }
}
