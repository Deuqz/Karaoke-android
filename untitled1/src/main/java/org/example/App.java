package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage() + "zxc123");
        }
        String url = "jdbc:postgresql://abul.db.elephantsql.com:5432/nspkcjrg", user = "nspkcjrg", password = "HvwCWSw9piCjZdNVL7MpHoBMSgCLDRFY";
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            System.out.println("Connection successful!\n");
        } catch (SQLException e) {
            System.out.println("Connection error\n" + e.getMessage());
        }
        assert statement != null;
        ResultSet result1 = statement.executeQuery(
                "SELECT * FROM users where password='aboba'");
        while (result1.next()) {
            System.out.println(result1.getRow() + "\t " + result1.getString("login"));
        }
//        Server server = ServerBuilder.forPort(8080).addService(new GreetingServiceImpl()).build();
//        server.start();
//        System.out.println("ABOBA!");
//        server.awaitTermination();
    }
}
