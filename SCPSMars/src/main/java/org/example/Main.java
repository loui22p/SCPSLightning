package org.example;

import com.sun.net.httpserver.HttpServer;
import org.example.controllers.DayController;
import org.example.controllers.LightningController;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.out.println("Now: " + LocalDate.now());
        //db.numberOfLightningsWeek(LocalDate.now());

        DatabaseHandler db = DatabaseHandler.getInstance();
        db.setup();

        //Setting up server to host backend
        // Create HTTP server listening on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // Create a context for "/api/data" endpoint
//        server.createContext("/api/tenMinutes", new SimpleHttpServer.tenMinuteHandler());
//        server.createContext("/api/day", new SimpleHttpServer.TodayHandler());
//        server.createContext("/api/week", new SimpleHttpServer.ThisWeekHandler());
//        server.createContext("/api/fullWeek", new SimpleHttpServer.FullWeekHandeler());


        server.createContext("/api/day/fullWeek", new DayController());
        server.createContext("/api/day/week", new DayController());
        server.createContext("/api/day/day", new DayController());
        server.createContext("/api/lightnings/cloudToGround", new LightningController());
        server.createContext("/api/lightnings/cloudToCloud", new LightningController());
        server.createContext("/api/lightnings/tenMinutes", new LightningController());

        server.setExecutor(null); // Use the default executor

        // Start the server
        server.start();
        System.out.println("Server started on port 8000");
    }
}

