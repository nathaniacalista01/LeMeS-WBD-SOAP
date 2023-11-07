package com.LMS.models;

import jdk.jpackage.internal.Log;

public class Logging {
    private String description;
    private String ip;
    private String endpoint;
    public Logging(String description, String ip, String endpoint){
        this.description= description;
        this.ip = ip;
        this.endpoint = endpoint;
    }

    public String getDescription(){
        return this.description;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getIp() {
        return ip;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
