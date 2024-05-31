package org.example.controllers;

import com.sun.net.httpserver.HttpExchange;
import org.example.daos.DayDao;
import org.example.models.Day;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

public class DayController extends BaseController {

    private Day day;

    public DayController() {
        this.day = new Day();
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
            case "/api/day/day":
                handleLightningsToday(exchange);
                break;
            case "/api/day/week":
                handleLightningsWeek(exchange);
                break;
            case "/api/day/fullWeek":
                handleLightningsWeekMap(exchange);
                break;
        }
    }

    private void handleLightningsToday(HttpExchange exchange) throws IOException {
        int totalCloudToGround = day.allLightningsToday();
        String response = Integer.toString(totalCloudToGround);
        sendResponse(exchange, 200, response);
    }

    private void handleLightningsWeek(HttpExchange exchange) throws IOException {
        int totalLightnings = day.numberOfLightningsWeek();
        String response = Integer.toString(totalLightnings);
        sendResponse(exchange, 200, response);
    }

    private void handleLightningsWeekMap(HttpExchange exchange) throws IOException {
        Map fullWeekMap = day.fullWeekLightnings();
        String response = fullWeekMap.toString();
        sendResponse(exchange, 200, response);
    }
}
