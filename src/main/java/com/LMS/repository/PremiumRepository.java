package com.LMS.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    public String updatePremiumStatus(int user_id, String newStatus){
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
}
