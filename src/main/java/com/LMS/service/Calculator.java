package com.LMS.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class Calculator {

    @WebMethod
    public int add(@WebParam(name = "param1") int a, @WebParam(name="param2") int b){
        return a + b;
    }
}
