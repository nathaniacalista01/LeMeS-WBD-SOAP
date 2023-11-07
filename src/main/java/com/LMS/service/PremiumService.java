package com.LMS.service;

import com.LMS.core.Database;
import com.LMS.repository.PremiumRepository;
import com.sun.net.httpserver.HttpExchange;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebService
public class PremiumService {
    LoggingService ls;
    PremiumRepository premiumRepository;
    @Resource
    public WebServiceContext ws;

    public PremiumService(){
        this.ls = new LoggingService();
        this.premiumRepository = new PremiumRepository();
    }
    @WebMethod
    public String testing(@WebParam(name="param1") String a){
        return a + "testing";
    }

    @WebMethod
    public boolean isPremium(@WebParam(name="user_id") int user_id){
//        MessageContext mc = this.ws.getMessageContext();
//        HttpExchange exchange = (HttpExchange)mc.get("com.sun.xml.ws.http.exchange");
//        InetSocketAddress remoteAddress = exchange.getRemoteAddress();
//        InetAddress inetAddress = remoteAddress.getAddress();
//        String ip = remoteAddress.getAddress().getHostAddress();
//        String description = "User with id " + Integer.toString(user_id) + " has called isPremium";
//        String endpoint = "isPremium";
//        ls.add(description,ip,endpoint);
        return this.premiumRepository.isPremium(user_id);
    }
    @WebMethod
    public String upgrade(@WebParam(name = "user_id") int user_id){
        return premiumRepository.upgrade(user_id);
    }

    @WebMethod
    public String updatePremiumStatus(@WebParam(name = "user_id")int user_id, @WebParam(name="newStatus") String newStatus){
        return premiumRepository.updatePremiumStatus(user_id, newStatus);
    }

    @WebMethod
    public String deleteRequest(@WebParam(name = "user_id") int user_id){
        return  premiumRepository.deleteRequest(user_id);
    }

    @WebMethod

    public String getPremiumStatus(@WebParam(name = "user_id") int user_id){
        return premiumRepository.getPremiumStatus(user_id);
    }


}
