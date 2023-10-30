package com.LMS.core;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static  Database instance;
    private  Connection conn;
    private Dotenv dotenv = Dotenv.load();
    private String MYSQL_URL = dotenv.get("MYSQL_URL");
    private  String MYSQL_USER = dotenv.get("MYSQl_USER");
    private String MYSQL_PASSWORD = dotenv.get("MYSQL_PASSWORD");

    public Database(){
        try{
            Connection conn = DriverManager.getConnection(MYSQL_URL,"root",MYSQL_PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error connecting to database");
        }
    }

    public Connection getConnection(){
        return conn;
    }

    public static Database getInstance(){
        try{
            if(instance == null ||instance.getConnection().isClosed()){
                instance = new Database();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return instance;
    }
}
