package com.example.lurenjiaspring.config.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;


public class LogBackCustomComStdoutFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (event.getLevel() == Level.DEBUG) {
            //System.out.println("event.getLoggerName() = " + event.getLoggerName());
            switch (event.getLoggerName()) {
                case "org.springframework.cloud.sleuth.instrument.web.ExceptionLoggingFilter":
                    return FilterReply.DENY;
                case "com.example.lurenjiaspring.config.logconfig.MySQLSqlAppender":
                    return FilterReply.ACCEPT;
            }
            return FilterReply.DENY;
        }
        return FilterReply.DENY;
    }

}