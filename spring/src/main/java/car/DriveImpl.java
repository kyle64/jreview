package car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ziheng on 2019-08-05.
 */
@Component
public class DriveImpl implements Drive {
    private Car myCar;

    @Autowired
    public void setMyCar(Car myCar) {
        this.myCar = myCar;
    }

    @Override
    public void drive() {
        System.out.println(String.format("drive my %s %s car", myCar.getMake(), myCar.getModel()));
    }

    @Override
    public void park() {
        System.out.println(String.format("park my %s %s car", myCar.getMake(), myCar.getModel()));
    }
}
