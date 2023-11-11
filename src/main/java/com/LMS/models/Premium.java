package com.LMS.models;

public class Premium {
    private String user_id;
    private String status;

    private String username;
    public Premium(String user_id, String status, String username){
        this.user_id = user_id;
        this.status = status;
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
