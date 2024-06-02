package org.example.models;

import com.google.gson.JsonArray;
import org.example.ApiConnection;
import org.example.daos.LightningDao;

public class Lightning {

    private LightningDao lightningDao;

    public Lightning() {
        this.lightningDao = new LightningDao();
    }

    public int allCloudToGroundLightningsToday() {
        int numberLightningsType1 = lightningDao.numberOfLightningsType(1);
        int numberLightningsType2 = lightningDao.numberOfLightningsType(2);

        int totalCloudToGroundLightnings = numberLightningsType1 + numberLightningsType2;
        return totalCloudToGroundLightnings;
    }

    public int allCloudToCloudLightningsToday() {
        int numberLightningsType3 = lightningDao.numberOfLightningsType(3);
        return numberLightningsType3;
    }
}
