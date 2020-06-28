import car.Drive;
import car.DriverConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ziheng on 2019-08-05.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DriverConfig.class)
public class DriveTest {
    @Autowired
    Drive myDrive;

    @Test
    public void firstDrive() {
        myDrive.drive();
        myDrive.park();
    }
}
