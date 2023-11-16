package com.LMS.service;

import com.LMS.middleware.LoggingMiddleware;
import com.LMS.repository.PremiumRepository;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

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
    public boolean isPremium(@WebParam(name="user_id") int user_id){
        LoggingMiddleware middleware = new LoggingMiddleware(ws.getMessageContext(),user_id,"check her/his premium status","isPremium");
        return this.premiumRepository.isPremium(user_id);
    }
    @WebMethod
    public String upgrade(@WebParam(name = "user_id") int user_id){
        LoggingMiddleware middleware = new LoggingMiddleware(ws.getMessageContext(),user_id,"request for premium account", "upgrade");
        return premiumRepository.upgrade(user_id);
    }

    @WebMethod
    public String updatePremiumStatus(@WebParam(name = "user_id")int user_id, @WebParam(name="newStatus") String newStatus){
//        Ini nanti klo bisa, tampilin id yang nge ganti si premium status
        LoggingMiddleware middleware = new LoggingMiddleware(ws.getMessageContext(),user_id,"Has requested to update to update " + user_id + "'s status to " + newStatus, "updatePremiumStatus");
        try{
            String result = premiumRepository.updatePremiumStatus(user_id, newStatus);
            return result;
        }catch (Exception e){
            System.out.println("Masuk ke error sini");
            e.printStackTrace();
            return "Error";
        }
    }

    @WebMethod
    public String deleteRequest(@WebParam(name = "user_id") int user_id){
        LoggingMiddleware middleware = new LoggingMiddleware(ws.getMessageContext(),user_id, "Has requested to delete premium accounts for user with id " + user_id, "deleteRequest");
        return  premiumRepository.deleteRequest(user_id);
    }

    @WebMethod
    public String getPremiumStatus(@WebParam(name = "user_id") int user_id){
        LoggingMiddleware middleware = new LoggingMiddleware(ws.getMessageContext(),user_id,"Has requested to see his/her premium's status","getPremiumStatus");
        return premiumRepository.getPremiumStatus(user_id);
    }

    @WebMethod
    public String getAllPremium(){
        return premiumRepository.getAllPremium();
    }

    @WebMethod
    public String filterPremium(@WebParam(name = "filter") String filter){
        return premiumRepository.filterPremium(filter);
    }

    @WebMethod
    public String searchPremium(@WebParam(name = "username") String username){
        return premiumRepository.searchPremium(username);
    }

    @WebMethod
    public String searchAndFilterPremium(@WebParam(name = "username") String username, @WebParam(name = "status") String status){
        return premiumRepository.searchAndFilterPremium(username,status);
    }

    @WebMethod
    public String getPremiumPagination(@WebParam(name="page")int page, @WebParam(name ="limit") int limit){
        return premiumRepository.getPagination(page,limit);
    }
    @WebMethod
    public Integer getTotalPremium(){
        return premiumRepository.getTotalPremiumAccounts();
    }

}
