package com.books.test;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.books.utils.JDBCUtils;
import com.mysql.jdbc.Driver;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class TestConnection {
    @Test
    public void test1() {
        Connection connect = null;
        try {
            Driver driver = new Driver();
            String url = "jdbc:mysql://192.168.141.129:3306/bms";
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "963.963.");
            connect = driver.connect(url, properties);
            System.out.println(connect);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connect != null) {
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void test2() {
        try {
            Properties properties = new Properties();
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties"));
            DataSource source = DruidDataSourceFactory.createDataSource(properties);
            Connection conn = source.getConnection();
            System.out.println(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        Connection connection = JDBCUtils.connection();
        System.out.println(connection);
    }
}
