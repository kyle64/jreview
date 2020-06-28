import com.review.mybatis.entity.TtestPO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ziheng on 2020/6/9.
 */
public class MybatisTest {

    @Test
    public void insert() {
        TtestPO ttestPO = new TtestPO();
        ttestPO.setData(new Random().nextInt(20));
        ttestPO.setExpression("california");

        int ret = MyBatisConfig.getInstance().insert(ttestPO);
        Assert.assertTrue(ret > 0);
    }

    @Test
    public void batchInsert() {
        List<TtestPO> list = new ArrayList<>();

        TtestPO ttestPO1 = new TtestPO();
        ttestPO1.setData(new Random().nextInt(20));
        ttestPO1.setExpression("cafe1");

        TtestPO ttestPO2 = new TtestPO();
        ttestPO2.setData(new Random().nextInt(20));
        ttestPO2.setExpression("coke1");

        TtestPO ttestPO3 = new TtestPO();
        ttestPO3.setData(new Random().nextInt(20));
        ttestPO3.setExpression("cake1");

        list.add(ttestPO1);
        list.add(ttestPO2);
        list.add(ttestPO3);

        int ret = MyBatisConfig.getInstance().batchInsert(list);
        Assert.assertEquals(3, ret);
    }
}
