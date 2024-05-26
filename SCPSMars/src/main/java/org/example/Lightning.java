package org.example;

public class Lightning {
    Database database;

    public Lightning() {
        this.database = database;
    }

    public static void getAllForDay(){
        database.executeStatement("SELECT * FROM lightningdb.lightning WHERE day_id = ? AND type_id = ?");
    }


}
