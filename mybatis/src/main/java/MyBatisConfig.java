import com.github.pagehelper.PageHelper;
import com.review.mybatis.MyTestMapper;
import com.review.mybatis.PageContextHolder;
import com.review.mybatis.entity.TtestPO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.List;
import java.util.Properties;
import java.util.Random;


/**
 * Created by ziheng on 2020/5/13.
 */
public class MyBatisConfig {
    private static volatile MyBatisConfig instance;
    private static SqlSessionFactory sqlSessionFactory;

    private MyBatisConfig() {
    }

    public static MyBatisConfig getInstance() {
        if (instance == null) {
            synchronized (MyBatisConfig.class) {
                if (instance == null) {
                    instance = new MyBatisConfig();
                    init();
                }
            }
        }
        return instance;
    }

    private static void init() {
        try {
//        InputStream stream = getClass().getClassLoader().getResourceAsStream("mybatis-configd.xml");
//        InputStream stream = MyBatisConfig.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            Properties properties = new Properties();
            properties.setProperty("username", "root");
            properties.setProperty("password", "Cisco123");
            properties.setProperty("url", "jdbc:mysql://localhost:3306/test");
            properties.setProperty("driver", "com.mysql.jdbc.Driver");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, properties);
        } catch (Exception e) {

        }
    }

    public void testT1() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MyTestMapper myTestMapper = sqlSession.getMapper(MyTestMapper.class);
        // row bounds 会先取出所有的数据，再根据offset和limit返回最后的结果
//        PageHelper.startPage(1, 3);
        System.out.println(myTestMapper.selectT1Data());

        PageContextHolder.setPage(1, 2);
        TtestPO condition = new TtestPO();
        condition.setExpression("c");
        System.out.println(myTestMapper.selectByLikePrefix(condition));

        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    public int insert(TtestPO ttestPO) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            MyTestMapper myTestMapper = sqlSession.getMapper(MyTestMapper.class);
            int row = myTestMapper.insertOne(ttestPO);
            sqlSession.commit();

            return row;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int batchInsert(List<TtestPO> ttestPOList) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            MyTestMapper myTestMapper = sqlSession.getMapper(MyTestMapper.class);
            int row = myTestMapper.batchInsert(ttestPOList);
            sqlSession.commit();

            return row;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }


    public static void main(String[] args) {
        MyBatisConfig.getInstance().testT1();
    }
}
