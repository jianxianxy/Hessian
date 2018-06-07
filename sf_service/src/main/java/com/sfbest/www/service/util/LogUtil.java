package com.sfbest.www.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    private LogUtil() {}

    public static Logger getLogger(LoggerName loggerName){
       return LoggerFactory.getLogger(loggerName.getName());
    }


    public enum LoggerName{

        ACTIVITY("activity"),
        REDIS("redis");

        private String name;

        LoggerName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
