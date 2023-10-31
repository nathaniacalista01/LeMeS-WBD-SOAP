package com.LMS.service;

import com.LMS.core.Database;
import com.mysql.cj.protocol.Resultset;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebService
public class Subsciption {
    @WebMethod
    public String testing(@WebParam(name="param1") String a){
        return a + "testing";
    }

    @WebMethod
    public boolean isSubscribed(@WebParam(name="user_id") int user_id){
        try{
            Database db = Database.getInstance();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM subscriptions where user_id = " + user_id + " AND status = 'ACCEPTED'";
            ResultSet result = stmt.executeQuery(query);
            if(result.next()){
                return true;
            }
        }catch (Exception e){
            System.out.println("Error...");
            return false;
        }
        return false;
    }

}
