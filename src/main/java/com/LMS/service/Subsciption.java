package com.LMS.service;

import com.LMS.core.Database;
import com.mysql.cj.protocol.Resultset;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.swing.plaf.nimbus.State;
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

    @WebMethod
    public String subscribe(@WebParam(name = "user_id") int user_id){
        try{
            Database db =Database.getInstance();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO subscriptions(user_id,status) VALUES(" + user_id + ",'WAITING')";
            stmt.executeUpdate(query);
            return "Successfully request for premium status";
        }catch (Exception e){
            e.printStackTrace();
            return "Request failed";
        }
    }

    @WebMethod
    public String updateSubscription(@WebParam(name = "user_id")int user_id, @WebParam(name="newStatus") String newStatus){
        try {
            Database db = Database.getInstance();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String query = "UPDATE subscriptions  SET status = '" + newStatus + "' WHERE user_id = " + user_id;

            int rows = stmt.executeUpdate(query);
            System.out.println(rows);
            if(rows == 0){
                return "User doesn't exists";
            }
            return "Succesfully updated subscriptionts status!";
        }catch (Exception e){
            e.printStackTrace();
            return "Update failed";
        }
    }

    @WebMethod
    public String deleteSubscription(@WebParam(name = "user_id") int user_id){
        try{
            Database db = Database.getInstance();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String query = "DELETE FROM subscriptions WHERE user_id = " + user_id;
            int row = stmt.executeUpdate(query);
            if(row == 0){
                return "User doesn't exist";
            }
            return "User has been successfully deleted!";
        }catch(Exception e){
            return  "Delete failed!";
        }
    }


}
