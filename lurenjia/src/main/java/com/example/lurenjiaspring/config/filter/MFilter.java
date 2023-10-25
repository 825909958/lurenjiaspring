package com.example.lurenjiaspring.config.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import org.springframework.stereotype.Component;

@Component
public class MFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (event.getLevel() == Level.ERROR) {
            System.out.println("event.getLoggerName() = " + event.getLoggerName());
            switch (event.getLoggerName()) {
                case "org.springframework.cloud.sleuth.instrument.web.ExceptionLoggingFilter":
                    return FilterReply.DENY;
            }
            return FilterReply.ACCEPT;
        }
        return FilterReply.DENY;
    }

}