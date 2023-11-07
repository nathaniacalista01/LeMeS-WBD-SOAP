package com.LMS.repository;

import com.LMS.core.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Repository {
    protected Database db;
    protected Connection conn;
    protected Statement stmt;
    public Repository(){
        try{
            this.db = Database.getInstance();
            this.conn = this.db.getConnection();
            this.stmt = this.conn.createStatement();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
