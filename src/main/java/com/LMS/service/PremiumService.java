package com.LMS.service;

import com.LMS.middleware.ApiMiddleware;
import com.LMS.middleware.LoggingMiddleware;
import com.LMS.models.Logging;
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
        try{
            LoggingMiddleware middleware = new LoggingMiddleware(ws.getMessageContext(),"check her/his premium status","isPremium");
            return this.premiumRepository.isPremium(user_id);
        }catch(Exception e){
            return false;
        }
    }
    @WebMethod
    public String upgrade(@WebParam(name = "user_id") int user_id){
        try{
            LoggingMiddleware middleware = new LoggingMiddleware(ws.getMessageContext(),"User has requested for premium account", "upgrade");
            return premiumRepository.upgrade(user_id);
        }catch (Exception e){
            return "API EXCEPTION";
        }

    }

    @WebMethod
    public String updatePremiumStatus(@WebParam(name = "user_id")int user_id, @WebParam(name="newStatus") String newStatus){
        try{
            LoggingMiddleware middleware = new LoggingMiddleware(ws.getMessageContext(),"User has requested to update to update " + user_id + "'s status to " + newStatus, "updatePremiumStatus");
        }catch (Exception e){
            return "API EXCEPTION";
        }

        try{
            String result = premiumRepository.updatePremiumStatus(user_id, newStatus);
            return result;
        }catch (Exception e){
            return "Error";
        }
    }

    @WebMethod
    public String deleteRequest(@WebParam(name = "user_id") int user_id){
        try{
            LoggingMiddleware middleware = new LoggingMiddleware(ws.getMessageContext(), "User has requested to delete premium accounts for user with id " + user_id, "deleteRequest");
        }catch (Exception e){
            return "API EXCEPTION";
        }
        return  premiumRepository.deleteRequest(user_id);
    }

    @WebMethod
    public String getPremiumStatus(@WebParam(name = "user_id") int user_id){
        try{
            LoggingMiddleware middleware = new LoggingMiddleware(ws.getMessageContext(),"User has requested to see his/her premium's status","getPremiumStatus");
        }catch (Exception e){
            return "API EXCEPTION";
        }
        return premiumRepository.getPremiumStatus(user_id);
    }

    @WebMethod
    public String getAllPremium(){
        try{
            LoggingMiddleware middleware = new LoggingMiddleware(ws.getMessageContext(),"User Requested to get all premium data","/getAllPremium");
        }catch (Exception e){
            return "API EXCEPTION";
        }
        return premiumRepository.getAllPremium();
    }

    @WebMethod
    public String filterPremium(@WebParam(name = "filter") String filter){
        try{
            LoggingMiddleware middleware = new LoggingMiddleware(ws.getMessageContext(),"User Requested to filter premium data","/filterPremium");
        }catch (Exception e){
            return "API EXCEPTION";
        }
        return premiumRepository.filterPremium(filter);
    }

    @WebMethod
    public String searchPremium(@WebParam(name = "username") String username){
        try{
            LoggingMiddleware middleware = new LoggingMiddleware(ws.getMessageContext(),"User requested to search premium data","/searchPremium");
        }catch (Exception e){
            return "API EXCEPTION";
        }
        return premiumRepository.searchPremium(username);
    }

    @WebMethod
    public String searchAndFilterPremium(@WebParam(name = "username") String username, @WebParam(name = "status") String status){
        try{
            LoggingMiddleware middleware = new LoggingMiddleware(ws.getMessageContext(),"User requested to search and filter premium data","/searchAndFilterPremium");
        }catch (Exception e){
            return "API EXCEPTION";
        }
        return premiumRepository.searchAndFilterPremium(username,status);
    }

    @WebMethod
    public String getPremiumPagination(@WebParam(name="page")int page, @WebParam(name ="limit") int limit){
        try{
            LoggingMiddleware middleware = new LoggingMiddleware(ws.getMessageContext(),"User requested to get all premium data with pagination","/searchAndFilterPremium");
        }catch (Exception e){
            return "API EXCEPTION";
        }
        return premiumRepository.getPagination(page,limit);
    }
    @WebMethod
    public Integer getTotalPremium(){
        try{
            LoggingMiddleware middleware = new LoggingMiddleware(ws.getMessageContext(),"User requested to search and filter premium data","/searchAndFilterPremium");
        }catch (Exception e){
            return 404;
        }
        return premiumRepository.getTotalPremiumAccounts();
    }

}
