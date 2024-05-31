package org.example.daos;

import org.example.DatabaseHandler;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DayDao {

    DatabaseHandler databaseHandler = DatabaseHandler.getInstance();

    public int getCurrentDayId() {
        try (ResultSet querryResultSet = databaseHandler.executeSqlWithDateParam("SELECT * FROM lightningdb.day WHERE date = ?", Date.valueOf(LocalDate.now()))) {
            querryResultSet.next();
            int id = querryResultSet.getInt("id");
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
