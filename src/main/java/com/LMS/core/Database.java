package com.LMS.core;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static  Database instance;
    private  Connection conn;
    private Dotenv dotenv = Dotenv.load();
    private String MYSQL_URL = dotenv.get("MYSQL_URL");
    private  String MYSQL_USER = dotenv.get("MYSQL_USER");
    private String MYSQL_PASSWORD = dotenv.get("MYSQL_PASSWORD");

    private Database(){
        int restart = 0;
        boolean connected = false;
        while(!connected){
            if(restart == 5){
                break;
            }
            try{
                Thread.sleep(5000);
                System.out.println(MYSQL_URL + MYSQL_USER + MYSQL_PASSWORD);
                String table = "CREATE TABLE IF NOT EXISTS premium_accounts (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "user_id INT, " +
                        "status ENUM('WAITING', 'ACCEPTED', 'REJECTED'))";
                this.conn = DriverManager.getConnection(MYSQL_URL,MYSQL_USER,MYSQL_PASSWORD);

                Statement stmt = conn.createStatement();
                stmt.execute(table);
                connected = true;
            }catch (Exception e){
                System.out.println("Error connecting to database");
                restart+=1;
            }
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
