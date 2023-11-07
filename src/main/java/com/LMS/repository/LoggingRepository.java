package com.LMS.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoggingRepository extends Repository {
    public LoggingRepository(){
        super();
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
