package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class LightningController extends BaseController {

    LightningDao lightningDao;

    public LightningController(LightningDao lightningDao) {
        this.lightningDao = lightningDao;
    }

    @Override
    public void handleCount(HttpExchange exchange) throws IOException {
        ArrayList<Lightning> lightning = lightningDao.getAllLightnings();
        String count = String.valueOf(lightning.size());
        sendResponse(exchange, 200, count);
    }

}
