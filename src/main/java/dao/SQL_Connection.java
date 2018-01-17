package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL_Connection {
    private static Connection connection;

    public static Connection getDBConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/users";
        String jdbcUsername = "root";
        String jdbcPassword = "root";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e){
            throw new SQLException(e);
        }
        connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        return connection;
    }

    public static void closeDBConnection() throws SQLException {
            connection.close();
        }
    }


