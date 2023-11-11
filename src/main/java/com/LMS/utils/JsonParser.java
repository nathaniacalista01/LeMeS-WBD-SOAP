package com.LMS.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public String parse(ResultSet rows) throws  SQLException{
        String result = "{ \"data\" : [";
        List<String> premiumList = new ArrayList<String>();
        try{
            while(rows.next()){
                int userId = rows.getInt("user_id");
                String username = rows.getString("username");
                String status = rows.getString("status");

                // Membangun objek JSON untuk setiap baris hasil
                String json = String.format("{\"user_id\": %d, \"username\": \"%s\", \"status\": \"%s\"}",
                        userId, username, status);
                premiumList.add(json);
            }
            result += String.join(",", premiumList);
            result += "]}";
        }catch (SQLException e){
            throw new SQLException(e);
        }
        return result;
    }

}
