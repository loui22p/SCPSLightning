package org.example.daos;

import org.example.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class LightningDao {

    private DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
    private DayDao dayDao = new DayDao();

    public int numberOfLightningsType(int typeId) {
        int dayId = dayDao.getCurrentDayId();
        int amount = 0;
        // querry database for amount of lightning on a specific day
        try (ResultSet querryResultSet = databaseHandler.executeSqlWithTwoIntParam("SELECT * FROM lightningdb.lightning WHERE day_id = ? AND type_id = ?", dayId, typeId)){
            while (querryResultSet.next()) {
                amount++;
            }
            return amount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertIntoDB(LocalDate date, LocalDateTime localDateTime, int type) {
        // insert lightning into database
        int dayId = dayDao.saveDate(date);
        Timestamp timestamp = Timestamp.valueOf(localDateTime);

        try {
            databaseHandler.executeSqlWithTimestampAndTwoIntParam("INSERT INTO lightningdb.lightning (timestamp, day_id, type_id) VALUES (?,?,?)", timestamp, dayId, type);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
