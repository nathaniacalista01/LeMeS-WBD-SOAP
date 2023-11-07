package com.LMS.service;

import com.LMS.core.Database;
import com.sun.net.httpserver.HttpExchange;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebService
public class Premium {
    Database db;
    Connection conn;
    Statement stmt;
    LoggingService ls;
    @Resource
    public WebServiceContext ws;

    public Premium(){
        try {
            this.db = Database.getInstance();
            this.conn = db.getConnection();
            this.stmt = this.conn.createStatement();
            this.ls = new LoggingService();
        }catch (SQLException e){
            System.out.println("Error");
        }
    }
    @WebMethod
    public String testing(@WebParam(name="param1") String a){
        return a + "testing";
    }

    @WebMethod
    public boolean isPremium(@WebParam(name="user_id") int user_id){
        MessageContext mc = this.ws.getMessageContext();
        HttpExchange exchange = (HttpExchange)mc.get("com.sun.xml.ws.http.exchange");
        InetSocketAddress remoteAddress = exchange.getRemoteAddress();
        InetAddress inetAddress = remoteAddress.getAddress();
        String ip = remoteAddress.getAddress().getHostAddress();
        String description = "User with id " + Integer.toString(user_id) + " has called isPremium";
        String endpoint = "isPremium";
        ls.add(description,ip,endpoint);
        try{
            Database db = Database.getInstance();
            Connection conn = db.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM premium_accounts where user_id = " + user_id + " AND status = 'ACCEPTED'";
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
    public String upgrade(@WebParam(name = "user_id") int user_id){
        try{
            String query = "INSERT INTO premium_accounts(user_id,status) VALUES(" + user_id + ",'WAITING')";
            this.stmt.executeUpdate(query);
            return "Successfully request for premium status";
        }catch (Exception e){
            e.printStackTrace();
            return "Request failed";
        }
    }

    @WebMethod
    public String updatePremiumStatus(@WebParam(name = "user_id")int user_id, @WebParam(name="newStatus") String newStatus){
        try {
            String query = "UPDATE premium_accounts  SET status = '" + newStatus + "' WHERE user_id = " + user_id;
            int rows = this.stmt.executeUpdate(query);
            System.out.println(rows);
            if(rows == 0){
                return "User doesn't exists";
            }
            return "Succesfully updated premium status!";
        }catch (Exception e){
            e.printStackTrace();
            return "Update failed";
        }
    }

    @WebMethod
    public String deleteRequest(@WebParam(name = "user_id") int user_id){
        try{
            String query = "DELETE FROM premium_accounts WHERE user_id = " + user_id;
            int row = this.stmt.executeUpdate(query);
            if(row == 0){
                return "User doesn't exist";
            }
            return "User has been successfully deleted!";
        }catch(Exception e){
            return  "Delete failed!";
        }
    }

    @WebMethod

    public String getPremiumStatus(@WebParam(name = "user_id") int user_id){
        try{
            String query = "SELECT status FROM premium_accounts WHERE user_id = " + user_id;
            ResultSet result = this.stmt.executeQuery(query);
            if(result.next()){
                return result.getString("status");
            }else{
                return "NOT PREMIUM";
            }
        }catch(SQLException e){
            return "Error";
        }
    }


}
