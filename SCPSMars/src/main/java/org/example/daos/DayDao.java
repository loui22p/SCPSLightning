package org.example.daos;

import org.example.DatabaseHandler;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DayDao {

    private DatabaseHandler databaseHandler = DatabaseHandler.getInstance();

    public int getCurrentDayId() {
        try (ResultSet querryResultSet = databaseHandler.executeSqlWithDateParam("SELECT * FROM lightningdb.day WHERE date = ?", Date.valueOf(LocalDate.now()))) {
            querryResultSet.next();
            int id = querryResultSet.getInt("id");
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int numberOfLightningsDay(LocalDate localdate) {
        Date date = Date.valueOf(localdate);
        int amount = 0;
        // querry database for amount of lightning on a specific day
        try (ResultSet querryResultSet = databaseHandler.executeSqlWithDateParam("SELECT * FROM lightningdb.day WHERE date = ?", date)){
            if (querryResultSet.next()) {
                amount = querryResultSet.getInt("total_lightnings");
            } else {
                amount = 0;
            }
            return amount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int saveDate(LocalDate localDate) {
        Date date = Date.valueOf(localDate);
        //Check if date is already saved to the database
        try (ResultSet querryResultSet = databaseHandler.executeSqlWithDateParam("SELECT * FROM lightningdb.day WHERE date = ?", date)){

            if (!querryResultSet.next()) {
                // Inserting the new day in the database
                try (ResultSet newDateInsert = databaseHandler.executeSqlWithIntAndDateParam("INSERT INTO lightningdb.day (total_lightnings, date) VALUES (?, ?)", 1, date)){}

            } else {
                // Updating the amount of lightnings for the already existing day
                int lightnings = querryResultSet.getInt("total_lightnings");
                try(ResultSet existingDateUpdate = databaseHandler.executeSqlWithIntAndDateParam("UPDATE lightningdb.day SET total_lightnings = ? WHERE date=?", lightnings+1, date)){}
            }

            // Returning the id for the given date
            try(ResultSet resultSetDate = databaseHandler.executeSqlWithDateParam("SELECT * FROM lightningdb.day WHERE date = ?", date)){
                resultSetDate.next();
                return resultSetDate.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
