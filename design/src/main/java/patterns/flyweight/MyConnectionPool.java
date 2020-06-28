package patterns.flyweight;

import java.sql.*;
import java.util.Vector;

/**
 * Created by ziheng on 2019-09-09.
 */
public class MyConnectionPool {
    private static final String url = "jdbc:mysql://47.96.131.40:3306/sake?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static final String username = "dev";
    private static final String password = "MTk5NDA2MDQ=";
    private static final String driverClassName = "com.mysql.jdbc.Driver";

    private Vector<Connection> pool;
    private int poolSize = 3;

    private MyConnectionPool() {
        init();
    }

    public static MyConnectionPool getInstance() {
        return ConnectionHolder.instance;
    }

    private void init() {
        pool = new Vector<>(poolSize);

        for (int i = 0; i < poolSize; i++) {
            try {
                Class.forName(driverClassName);
                Connection conn = DriverManager.getConnection(url, username, password);
                pool.add(conn);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() throws SQLException {
        Connection connection = null;
        if (pool.size() > 0) {
            connection = pool.remove(0);
        }

        return connection;
    }

    public void release(Connection connection)  {
        pool.add(connection);
    }

    private static class ConnectionHolder {
        private static final MyConnectionPool instance = new MyConnectionPool();
    }

    public static void main(String[] args) throws Exception {
        String sql = "select id, gmt_create from bill_extension";
        long startTime = System.currentTimeMillis();

        MyConnectionPool pool = MyConnectionPool.getInstance();

        for (int i = 0; i < 100; i++) {
            Connection conn = pool.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
            }

            resultSet.close();
            statement.close();
            pool.release(conn);
        }

        System.out.println("time cost: " + (System.currentTimeMillis() - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            try {
                Class.forName(driverClassName);
                Connection conn = DriverManager.getConnection(url, username, password);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                }

                resultSet.close();
                statement.close();
                pool.release(conn);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("time cost: " + (System.currentTimeMillis() - startTime));

    }
}
