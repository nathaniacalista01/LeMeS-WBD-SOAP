package com.LMS;

import com.LMS.core.Database;
import com.LMS.service.Calculator;
import com.LMS.service.Subsciption;
import io.github.cdimascio.dotenv.Dotenv;

import javax.xml.ws.Endpoint;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Database db =  Database.getInstance();
        Endpoint.publish("http://0.0.0.0:8080/subscription",new Subsciption());
        System.out.println("Server is running...");
    }
}