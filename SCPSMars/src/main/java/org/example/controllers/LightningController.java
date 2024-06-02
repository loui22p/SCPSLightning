package org.example.controllers;

import com.google.gson.JsonArray;
import com.sun.net.httpserver.HttpExchange;
import org.example.ApiConnection;
import org.example.models.Lightning;

import java.io.IOException;

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
            case "/api/lightnings/cloudToGround":
                handleCloudToGround(exchange);
                break;
            case "/api/lightnings/cloudToCloud":
                handleCloudToCloud(exchange);
                break;
            case "/api/lightnings/tenMinutes":
                handleTenMinutes(exchange);
                break;
        }
    }

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

    private void handleTenMinutes(HttpExchange exchange) throws IOException {
        //Insert new data into db
        ApiConnection apiConnection = new ApiConnection();
        JsonArray jsonArray = apiConnection.retrieveData();

        //Use the newly retrieved data right away in view
        String response = Integer.toString(jsonArray.size());
        sendResponse(exchange, 200, response);
    }
}
