package car;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by ziheng on 2019-08-05.
 */
@Aspect
@Component
public class DriveClasspathAspect {
    @Pointcut("execution(* car.Drive.drive(..))")
    public void driveBehavior() {
    }

    @Pointcut("execution(* car.Drive.park(..))")
    public void parkBehavior() {
    }

    @Before("driveBehavior()")
    public void openDoor() {
        System.out.println("open the door");
    }

    @After("parkBehavior()")
    public void closeDoor() {
        System.out.println("close the door");
    }
}
