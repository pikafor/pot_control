package org.example;

import java.sql.*;

public class SqlConnection {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "1488";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private Connection connection;

    public void Connection() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void Close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
