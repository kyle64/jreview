package car;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by ziheng on 2019-08-06.
 */
@Component
public class CarContextAware implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CarContextAware.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(String name,Class<T> clz) throws BeansException {
        return applicationContext.getBean(name,clz);
    }

    public static <T> T getBean(Class<T> clz) throws BeansException {
        return applicationContext.getBean(clz);
    }
}
