package org.example.controllers;

import com.sun.net.httpserver.HttpExchange;
import org.example.models.Lightning;
import org.example.daos.LightningDao;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class LightningController extends BaseController {

    private Lightning lightning;

    public LightningController() {
        this.lightning = new Lightning();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
            setCorsHeaders(exchange);
            exchange.sendResponseHeaders(204, -1);
            return;
        }
        // Set response headers
        exchange.getResponseHeaders().set("Content-Type", "text/plain");
        setCorsHeaders(exchange);

        String path = exchange.getRequestURI().getPath();

        switch(path) {
//            case "/api/lightnings/count":
//                handleCount(exchange);
//                break;
            case "/api/lightnings/cloudToGround":
                handleCloudToGround(exchange);
                break;
            case "/api/lightnings/cloudToCloud":
                handleCloudToCloud(exchange);
                break;
        }
    }

//    public void handleCount(HttpExchange exchange) throws IOException {
//        ArrayList<Lightning> lightning = lightningDao.getAllLightnings();
//        String count = String.valueOf(lightning.size());
//        sendResponse(exchange, 200, count);
//    }

    private void handleCloudToGround(HttpExchange exchange) throws IOException {
        int totalCloudToGround = lightning.allCloudToGroundLightningsToday();
        String response = Integer.toString(totalCloudToGround);
        sendResponse(exchange, 200, response);
    }

    private void handleCloudToCloud(HttpExchange exchange) throws IOException {
        int totalCloudToGround = lightning.allCloudToCloudLightningsToday();
        String response = Integer.toString(totalCloudToGround);
        sendResponse(exchange, 200, response);
    }
}