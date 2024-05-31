package org.example.daos;

import org.example.DatabaseHandler;
import org.example.models.Lightning;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LightningDao {

    DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
    DayDao dayDao = new DayDao();

//    public ArrayList<Lightning> getAllLightnings() {
//        try {
//            ResultSet querryResultSet = databaseHandler.executeSql("SELECT * FROM lightningdb.lightning");
//
//            ArrayList<Lightning> lightnings = new ArrayList<Lightning>();
//            while (querryResultSet.next()) {
//                Lightning lightning = new Lightning();
//                lightnings.add(lightning);
//            }
//
//            return lightnings;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

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
}
