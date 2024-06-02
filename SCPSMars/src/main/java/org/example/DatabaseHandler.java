package org.example;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class DatabaseHandler {
    static Connection connection;
    static Dotenv dotenv = Dotenv.load();
    private static DatabaseHandler databaseInstance;

    public void setup() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lightning","postgres", dotenv.get("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet executeSql(String sql) throws SQLException {
        try {
            PreparedStatement queryStatement = connection.prepareStatement(sql);
            return queryStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet executeSqlWithDateParam(String sql, Date date) throws SQLException {
        try {
            PreparedStatement queryStatement = connection.prepareStatement(sql);
            queryStatement.setDate(1, date);
            return queryStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet executeSqlWithTwoIntParam(String sql, int int1, int int2) throws SQLException {
        try {
            PreparedStatement queryStatement = connection.prepareStatement(sql);
            queryStatement.setInt(1, int1);
            queryStatement.setInt(2, int2);
            return queryStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet executeSqlWithIntAndDateParam(String sql,int i, Date date) throws SQLException {
        try {
            PreparedStatement queryStatement = connection.prepareStatement(sql);
            queryStatement.setInt(1, i);
            queryStatement.setDate(2, date);
            return queryStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet executeSqlWithTimestampAndTwoIntParam(String sql, Timestamp timestamp, int int1, int int2) throws SQLException {
        try {
            PreparedStatement queryStatement = connection.prepareStatement(sql);
            queryStatement.setTimestamp(1, timestamp);
            queryStatement.setInt(2, int1);
            queryStatement.setInt(3, int2);
            return queryStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DatabaseHandler getInstance() {
        if (databaseInstance == null) {
            databaseInstance = new DatabaseHandler();
        }
        return databaseInstance;
    }
}
