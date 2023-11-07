package com.LMS;

import com.LMS.core.Database;
import com.LMS.service.PremiumService;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        Database db =  Database.getInstance();
        Endpoint.publish("http://0.0.0.0:8080/premium",new PremiumService());
        System.out.println("Server is running...");
    }
}