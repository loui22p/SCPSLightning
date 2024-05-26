package org.example;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class DatabaseHandler {
    static Connection connection = null;
    static Dotenv dotenv = Dotenv.load();

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
}
