package main.solve;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private static final String URL = "jdbc:mariadb://112.184.113.85:3306/java_project";
    private static final String USER = "root";
    private static final String PASS = "Ofpdlfnfn0341!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
