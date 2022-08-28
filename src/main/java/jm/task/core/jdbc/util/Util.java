package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost";
        String dbName = "my_db";
        String userName = "bestuser";
        String password = "bestuser";

        return getMyConnection(hostName, dbName, userName, password);
    }

    private static Connection getMyConnection (String hostName, String dbName, String userName, String password) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        Connection connection = DriverManager.getConnection(connectionURL, userName, password);

        return connection;
    }
}
