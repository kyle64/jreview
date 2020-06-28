package patterns.strategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ziheng on 2019-09-17.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PriceRegion {
    double min() default Integer.MIN_VALUE;
    double max() default Integer.MAX_VALUE;
}
