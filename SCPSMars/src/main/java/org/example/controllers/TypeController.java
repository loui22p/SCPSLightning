package org.example.controllers;

import com.sun.net.httpserver.HttpExchange;
import org.example.models.Type;

import java.io.IOException;

public class TypeController extends BaseController{

    private Type type;
    public TypeController() {
        type = new Type();
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
        }
    }
}
