package com.LMS.service;

import com.LMS.core.Database;
import com.LMS.repository.LoggingRepository;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class LoggingService {

    private LoggingRepository loggingRepository;
    public LoggingService(){
        this.loggingRepository = new LoggingRepository();

    }

    public void add(String desc, String ip, String endpoint){
        this.loggingRepository.add(desc,ip,endpoint);
    }


}
