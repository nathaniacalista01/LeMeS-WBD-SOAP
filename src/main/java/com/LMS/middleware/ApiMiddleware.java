package com.LMS.middleware;

import com.sun.net.httpserver.HttpExchange;
import io.github.cdimascio.dotenv.Dotenv;


public class ApiMiddleware {
    private String REST_API_KEY;
    private String PHP_API_KEY;

    private Dotenv dotenv = Dotenv.load();

    public ApiMiddleware(){
        String rest_api_key = dotenv.get("REST_API_KEY");
        String php_api_key = dotenv.get("PHP_API_KEY");
        if(rest_api_key == ""){
            rest_api_key = "RestApp";
        }
        if(php_api_key == ""){
            php_api_key = "PHPApp";
        }
        this.REST_API_KEY = rest_api_key;
        this.PHP_API_KEY = php_api_key;

    }

    public Boolean authenticate(HttpExchange httpExchange){
        String apiKey = httpExchange.getRequestHeaders().getFirst("X-API-KEY");
        if(apiKey == null) {
            return false;
        }else if(apiKey.equals(this.PHP_API_KEY) || apiKey.equals(this.REST_API_KEY)){
            return true;
        }
        return false;
    }
}
