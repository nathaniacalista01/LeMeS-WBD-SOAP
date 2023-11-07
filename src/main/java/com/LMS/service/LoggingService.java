package com.LMS.service;

import com.LMS.core.Database;
import com.LMS.models.Logging;
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

    public void add(Logging log){
        this.loggingRepository.add(log);
    }


}
