package car;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by ziheng on 2019-08-05.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "car")
public class DriverConfig {
    @Bean
    public Car myCC() {
        return new Car("Volkswagen", "CC Sedan", 1);
    }
}
