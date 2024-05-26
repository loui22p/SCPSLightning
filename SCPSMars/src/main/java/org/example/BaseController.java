package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class BaseController implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Set response headers
        exchange.getResponseHeaders().set("Content-Type", "text/plain");
        setCorsHeaders(exchange);

        String path = exchange.getRequestURI().getPath();

        switch (path) {
            case "lightning/count":
                handleCount();
        }
    }

    protected void handleCount() throws IOException {};

    private void setCorsHeaders(HttpExchange exchange) {

    }

    protected void sendResponse(HttpExchange exchange, int status, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
