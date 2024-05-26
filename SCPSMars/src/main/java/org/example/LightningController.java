package org.example;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LightningController {

    public void numberOfLightningsDay(){
        // querry database for amount of lightning on a specific day
        try {
            querryResultSet = Lightning.getAllForDay();

            if (querryResultSet.next()) {
                return querryResultSet.getInt("total_lightnings");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
