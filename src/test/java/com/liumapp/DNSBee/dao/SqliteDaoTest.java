package com.liumapp.DNSBee.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by liumapp on 7/18/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext-*.xml"})
public class SqliteDaoTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void test() throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            // create a database connection
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("DROP TABLE IF EXISTS `person`");
            statement.executeUpdate("create table `person` (id int(11) NOT NULL AUTO_INCREMENT, name VARCHAR(255) DEFAULT NULL , PRIMARY  KEY (`id`))");
            statement.executeUpdate("insert into person values(1, 'leo')");
            statement.executeUpdate("insert into person values(2, 'yui')");
            ResultSet rs = statement.executeQuery("select * from person");
            while (rs.next()) {
                // read the result set
                System.out.println("name = " + rs.getString("name"));
                System.out.println("id = " + rs.getInt("id"));
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }

    @Ignore
    @Test
    public void createTable() throws SQLException {
        excute("drop table if exists User_Passport");
//        excute("CREATE TABLE ZonesFile (\n" +
//                "  `id` INTEGER primary key AUTOINCREMENT,\n" +
//                "  `type` INTEGER,\n" +
//                "  `name` string,\n" +
//                "  `text` string,\n" +
//                "  `user` string\n" +
//                ");\n");
        excute("CREATE TABLE User_Passport (\n" +
                "  `id` INTEGER primary key AUTOINCREMENT,\n" +
                "  `username` text UNIQUE,\n" +
                "  `passwordSalt` string,\n" +
                "  `salt` string,\n" +
                "  `ticket` text UNIQUE\n" +
                ");");
    }

    @Ignore
    @Test
    public void insert() throws SQLException {
        excute("insert into ZonesFile (`type`,`name`,`user`,`text`) values(1,'dev','public','127.0.0.1 i*.static.dp\n" +
                "127.0.0.1 *.local.dp')");
        excute("insert into ZonesFile (`type`,`name`,`user`,`text`) values(1,'none','public','')");
    }

    private void excute(String query) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            // create a database connection
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate(query);
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }

}
