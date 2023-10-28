package com.example.lurenjiaspring.config.logconfig;


import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.FileAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class MySQLSqlAppender extends AppenderBase<ILoggingEvent> {
    Logger logger = LoggerFactory.getLogger(MySQLSqlAppender.class);

    private StringBuilder sqlBuilder = new StringBuilder();
    private List<Object> parameters = new ArrayList<>();

    @Override
    protected void append(ILoggingEvent eventObject) {
        String message = eventObject.getMessage();
        if (message.startsWith("==>  Preparing: ")) {
            // 提取 SQL 语句
            String sql = message.substring("==> Preparing:".length()).trim();

            // 将 SQL 添加到缓存
            sqlBuilder.append(sql);

        } else if (message.startsWith("==> Parameters: ")) {
            // 提取参数
            String parametersString = message.substring("==>  Parameters:".length()).trim();
            String cleanedParametersString = parametersString.replaceAll("\\([^)]*\\)", "");
            List<Object> eventParameters = extractParameters(cleanedParametersString);

            // 将参数添加到缓存
            parameters.addAll(eventParameters);

        } else if (message.startsWith("<==    Updates: ")||message.startsWith("<==      Total: ")) {
            // 判断是否是最后一次调用
            //if (eventObject.getLoggerName().endsWith("dao") && eventObject.getLevel() == Level.DEBUG) {
                String formattedSql = formatSql(sqlBuilder.toString(), parameters, eventObject);
                logger.debug("MySQL " + formattedSql);

                // 清除已使用的 SQL 和参数
                sqlBuilder.setLength(0);
                parameters.clear();
            //}
        }
    }

    private List<Object> extractParameters(String parametersString) {
        List<Object> eventParameters = new ArrayList<>();

        // 在这里根据实际情况解析参数字符串
        // 示例中假设参数以逗号分隔
        String[] parameterValues = parametersString.split(",");
        for (String parameterValue : parameterValues) {
            eventParameters.add(parameterValue.trim());
        }

        return eventParameters;
    }

    private String formatSql(String sql, List<Object> parameters, ILoggingEvent event) {
        String formattedSql = sql;

        for (Object parameter : parameters) {
            String placeholder = "\\?";
            String parameterValue = getParameterValueAsString(parameter, event);
            formattedSql = formattedSql.replaceFirst(placeholder, Matcher.quoteReplacement(parameterValue));

            //formattedSql = formattedSql.replaceFirst(placeholder, parameterValue);
        }

        return formattedSql;
    }

    private String getParameterValueAsString(Object parameter, ILoggingEvent event) {
        if (parameter == null) {
            return "NULL";
        } else if (parameter instanceof String) {
            return "'" + parameter.toString().replace("'", "''") + "'";
        } else {
            return parameter.toString();
        }
    }



    private FileAppender<ILoggingEvent> fileAppender;

    //@Override
    //public void start() {
    //    if (fileAppender != null) {
    //        fileAppender.start();
    //    } else {
    //        addStatus(new ErrorStatus("FileAppender is not configured", this));
    //    }
    //    super.start();
    //}

    //@Override
    //public void stop() {
    //    if (fileAppender != null) {
    //        fileAppender.stop();
    //    }
    //    super.stop();
    //}

    public void setFileAppender(FileAppender<ILoggingEvent> fileAppender) {
        this.fileAppender = fileAppender;
    }
}