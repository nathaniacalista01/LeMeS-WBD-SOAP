package com.LMS.repository;

import com.LMS.utils.JsonParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PremiumRepository extends  Repository{
    private  JsonParser jp;
    public PremiumRepository(){

        super();
        this.jp = new JsonParser();
    }

    public boolean isPremium(int user_id){
        try{
            String query ="SELECT * FROM premium_accounts where user_id = " + user_id + " AND status = 'ACCEPTED'";
            ResultSet result = this.stmt.executeQuery(query);
            if(result.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            return false;
        }
    }

    public String upgrade(int user_id, String username){
        try{
            String query = "INSERT INTO premium_accounts(user_id,status,username) VALUES(" + user_id + ",'WAITING', '" + username + "')";
            this.stmt.executeUpdate(query);
            return "Successfully request for premium status";
        }catch (Exception e){
            e.printStackTrace();
            return "Request failed";
        }
    }

    public String updatePremiumStatus(int user_id, String newStatus) throws Exception{
        try {
            String query = "UPDATE premium_accounts  SET status = '" + newStatus + "' WHERE user_id = " + user_id;
            int rows = this.stmt.executeUpdate(query);
            if(rows == 0){
                return "Not Exists";
            }
            return "Successfully updated premium status!";
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public String deleteRequest(int user_id){
        try{
            String query = "DELETE FROM premium_accounts WHERE user_id = " + user_id;
            int row = this.stmt.executeUpdate(query);
            if(row == 0){
                return "Error";
            }
            return "User has been successfully deleted!";
        }catch(Exception e){
            return  "Error";
        }
    }

    public String getPremiumStatus(int user_id){
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

    public String getAllPremium(){
        try{
            String query = "SELECT * FROM premium_accounts";
            ResultSet rows = this.stmt.executeQuery(query);
            JsonParser jp = new JsonParser();
            String result = jp.parse(rows);
            return result;
        }catch (SQLException e){
            e.printStackTrace();
            return "{ \"data\" : []}";
        }
    }

    public String filterPremium(String filter){
        String result = "{ \"data\" : [";
        try{
            String query = "SELECT * FROM premium_accounts WHERE status = '" + filter + "'";
            ResultSet rows = this.stmt.executeQuery(query);
            List<String> premiumList = new ArrayList<String>();
            while (rows.next()) {
                int userId = rows.getInt("user_id");
                String username = rows.getString("username");
                String status = rows.getString("status");

                // Membangun objek JSON untuk setiap baris hasil
                String json = String.format("{\"user_id\": %d, \"username\": \"%s\", \"status\": \"%s\"}",
                        userId, username, status);
                premiumList.add(json);
            }

            // Menggabungkan semua objek JSON menjadi satu string
            result += String.join(",", premiumList);
            result += "]}";
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public String searchPremium(String searchQuery){
        String result = "{ \"data\" : [";
        try{
            String query = "SELECT * FROM premium_accounts WHERE username like '%" + searchQuery + "%'";
            ResultSet rows = this.stmt.executeQuery(query);
            List<String> premiumList = new ArrayList<String>();
            while (rows.next()) {
                int userId = rows.getInt("user_id");
                String username = rows.getString("username");
                String status = rows.getString("status");

                // Membangun objek JSON untuk setiap baris hasil
                String json = String.format("{\"user_id\": %d, \"username\": \"%s\", \"status\": \"%s\"}",
                        userId, username, status);
                premiumList.add(json);
            }

            // Menggabungkan semua objek JSON menjadi satu string
            result += String.join(",", premiumList);
            result += "]}";
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    public int getTotalPremiumAccounts(){
        try {
            String query = "SELECT COUNT(*) AS total FROM premium_accounts";
            ResultSet result = this.stmt.executeQuery(query);
            if (result.next()) {
                return result.getInt("total");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String searchAndFilterPremium(String username_params, String status_params){
        String result = "{ \"data\" : [";
        try{
            String query = "SELECT * FROM premium_accounts WHERE username like '%" + username_params + "%' AND status = '" + status_params + "'";
            ResultSet rows = this.stmt.executeQuery(query);
            List<String> premiumList = new ArrayList<String>();
            while (rows.next()) {
                int userId = rows.getInt("user_id");
                String username = rows.getString("username");
                String status = rows.getString("status");

                // Membangun objek JSON untuk setiap baris hasil
                String json = String.format("{\"user_id\": %d, \"username\": \"%s\", \"status\": \"%s\"}",
                        userId, username, status);
                premiumList.add(json);
            }

            // Menggabungkan semua objek JSON menjadi satu string
            result += String.join(",", premiumList);
            result += "]}";
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public String getPagination(int page, int limit){
        String result = "{ \"data\" : [";
        int offset = (page - 1) * limit;
        System.out.println("Ini offset" + offset);
        try{
            String query = "SELECT * FROM premium_accounts LIMIT " + limit + " OFFSET " + offset;
            ResultSet rows = this.stmt.executeQuery(query);
            List<String> premiumList = new ArrayList<String>();
            while (rows.next()) {
                int userId = rows.getInt("user_id");
                String username = rows.getString("username");
                String status = rows.getString("status");

                // Membangun objek JSON untuk setiap baris hasil
                String json = String.format("{\"user_id\": %d, \"username\": \"%s\", \"status\": \"%s\"}",
                        userId, username, status);
                premiumList.add(json);
            }

            // Menggabungkan semua objek JSON menjadi satu string
            result += String.join(",", premiumList);
            result += "]}";
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}
