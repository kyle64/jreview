package netty.rpc.provider;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.rpc.Request;
import netty.rpc.Response;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by ziheng on 2020/8/27.
 */
public class ConvertServiceHandler extends ChannelInboundHandlerAdapter {
    private static String INTERFACE_PATH = "netty.rpc.interfaces";
    private static String IMPL_PATH = "netty.rpc.provider";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Request request = (Request) msg;

        String result = (String) this.handle(request);

        Response response = new Response();
        response.setRequestId(request.getId());
        response.setContent(result);
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private Object handle(Request request) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {
        String interfaceName = request.getClassName();
        String methodName = request.getMethodName();
        Class interfaceClazz = Class.forName(INTERFACE_PATH + "." + interfaceName);
        String implName = getImplClassName(interfaceClazz);

        Object result = null;
        if (implName != null) {
            Object implService = Class.forName(implName).newInstance();
            Method method = implService.getClass().getMethod(methodName, request.getParameterTypes());
            result = method.invoke(implService, request.getParameters());
        }
        return result;
    }

    private String getImplClassName(Class interfaceClazz) {
        Reflections reflections = new Reflections(IMPL_PATH);
        // 得到接口的实现类
        Set<Class> subClassSet = reflections.getSubTypesOf(interfaceClazz);
        if (subClassSet.size() == 0) {
            System.out.println("未找到实现类");
            return null;
        } else if (subClassSet.size() > 1) {
            System.out.println("找到多个实现类");
            return null;
        } else {
            Class[] classes = subClassSet.toArray(new Class[0]);
            return classes[0].getName();//实现类的名字
        }
    }
}
