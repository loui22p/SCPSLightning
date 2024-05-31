package org.example.models;

import org.example.daos.DayDao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class Day {

    private DayDao dayDao;

    public Day() {
        this.dayDao = new DayDao();
    }

    public int allLightningsToday() {
        int lightnings = dayDao.numberOfLightningsDay(LocalDate.now());
        return lightnings;
    }

    public int numberOfLightningsWeek() {
        LocalDate date = LocalDate.now();
        int amount = 0;
        for (int i=0; i<7; i++) {
            amount = amount + dayDao.numberOfLightningsDay(date.minusDays(i));
        }
        return amount;
    }

    public Map<LocalDate, Integer> fullWeekLightnings() {
        LocalDate lastDay = LocalDate.now();
        LocalDate firstDay = lastDay.minusDays(6);
        Map map = new LinkedHashMap();
        for (int i=0; i<7; i++) {
            LocalDate date = firstDay.plusDays(i);
            map.put(date, dayDao.numberOfLightningsDay(date));
        }
        System.out.println(map);
        return map;
    }
}
