package com.LMS.service;

import com.LMS.models.Logging;
import com.LMS.repository.LoggingRepository;


public class LoggingService {

    private LoggingRepository loggingRepository;
    public LoggingService(){
        this.loggingRepository = new LoggingRepository();

    }

    public void add(Logging log){
        this.loggingRepository.add(log);
    }


}
