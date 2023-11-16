package com.LMS.middleware;

import com.LMS.models.Logging;
import com.LMS.service.LoggingService;
import com.sun.net.httpserver.HttpExchange;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class LoggingMiddleware {
    @Resource
    public WebServiceContext ws;
    private final static LoggingService loggingService = new LoggingService();
    public LoggingMiddleware(MessageContext mc, String action,String endpoint) throws Exception{
        HttpExchange exchange = (HttpExchange) mc.get("com.sun.xml.ws.http.exchange");
        ApiMiddleware api = new ApiMiddleware();
        if(!api.authenticate(exchange)){
//            Kalau API key tidak valid
            throw new Exception("API EXCEPTION");
        }
        InetSocketAddress remoteAddress  = exchange.getRemoteAddress();
        InetAddress inetAddress = remoteAddress.getAddress();
        String ip = inetAddress.getHostAddress();
        String description = "User " + action;
        Logging log = new Logging(description,ip,endpoint);
        loggingService.add(log);
    }


}
