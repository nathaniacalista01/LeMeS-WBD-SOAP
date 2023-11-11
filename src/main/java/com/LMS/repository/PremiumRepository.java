package com.LMS.repository;

import com.LMS.models.Premium;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.lang.model.type.ArrayType;
import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PremiumRepository extends  Repository{
    public PremiumRepository(){
        super();
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

    public String upgrade(int user_id){
        try{
            String query = "INSERT INTO premium_accounts(user_id,status) VALUES(" + user_id + ",'WAITING')";
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
            System.out.println(rows);
            if(rows == 0){
                return "User doesn't exists";
            }
            return "Succesfully updated premium status!";
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public String deleteRequest(int user_id){
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
        String result = "{ \"data\" : [";
        try{
            String query = "SELECT * FROM premium_accounts";
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
}
