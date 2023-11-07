package com.LMS.repository;

import com.LMS.models.Logging;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoggingRepository extends Repository {
    public LoggingRepository(){
        super();
    }

    public void add(Logging log){
        String query = "INSERT INTO logging(descriptions, IP, endpoint) VALUES (?, ?, ?)";
        String desc = log.getDescription();
        String ip = log.getIp();
        String endpoint = log.getEndpoint();
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
