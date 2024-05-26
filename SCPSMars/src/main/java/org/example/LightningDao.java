package org.example;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LightningDao {

    DatabaseHandler databaseHandler;

    public LightningDao(DatabaseHandler databaseHandler) {
        databaseHandler = databaseHandler;
    }

    public ArrayList<Lightning> getAllLightnings() {
        try {
            ResultSet querryResultSet = databaseHandler.executeSql("SELECT * FROM lightning");

            ArrayList<Lightning> lightnings = new ArrayList<Lightning>();
            while (querryResultSet.next()) {
                Lightning lightning = new Lightning();
                lightnings.add(lightning);
            }

            return lightnings;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
