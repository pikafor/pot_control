package org.example;

import java.sql.*;
import java.util.Scanner;

public class SqlRequest {
    private Connection connection;
    private Statement statement;
    private Scanner scanner = new Scanner(System.in);

    SqlRequest(Connection connection) {
        this.connection = connection;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setInt(PreparedStatement preparedStatement, int id, int value) {
        try {
            preparedStatement.setInt(id, value);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setString(PreparedStatement preparedStatement, int id, String value) {
        try {
            preparedStatement.setString(id, value);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void inputLine() {
        String sql = "insert into chipped(id, firstname, lastname, pot) values (?, ?, ?, ?);";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int id = scanner.nextInt();
        String firstName = scanner.next();
        String lastName = scanner.next();
        int pot = scanner.nextInt();

        setInt(preparedStatement, 1, id);
        setString(preparedStatement, 2, firstName);
        setString(preparedStatement, 3, lastName);
        setInt(preparedStatement, 4, pot);
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Print() {
        String sql = "select * from chipped";
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            //ResultSet resultSet;
            try {
                //resultSet = statement.executeQuery(sql);
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("firstName") +
                        " " + resultSet.getString("lastName") + " " + resultSet.getInt("pot"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void Delete(String lastName) {
        String sql = "delete from chipped where lastname = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            setString(preparedStatement, 1, lastName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void SearchPot(int pot) {
        String sql = "select * from chipped where pot = " + pot;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("firstName") +
                        " " + resultSet.getString("lastName") + " " + resultSet.getInt("pot"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public void UpdatePot(int pot, String lastName) {
        String sql = "update chipped set pot = ? where lastname = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            setInt(preparedStatement, 1, pot);
            setString(preparedStatement, 2, lastName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
