package com.LMS.service;

import com.LMS.core.Database;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class LoggingService {
    Database db;
    Connection conn;
    Statement stmt;
    public LoggingService(){
        try{
            this.db = Database.getInstance();
            this.conn = this.db.getConnection();
            this.stmt = this.conn.createStatement();
        }catch (SQLException e){
            System.out.println("Logging service error...");
        }
    }

    public void add(String desc, String ip, String endpoint){
        String query = "INSERT INTO logging(descriptions, IP, endpoint) VALUES (?, ?, ?)";
        try{
            PreparedStatement prep = this.conn.prepareStatement(query);
            prep.setString(1,desc);
            prep.setString(2,ip);
            prep.setString(3,endpoint);
            prep.executeUpdate();
        }catch (SQLException e){
            System.out.println("Insert logging fail...");
        }
    }


}
