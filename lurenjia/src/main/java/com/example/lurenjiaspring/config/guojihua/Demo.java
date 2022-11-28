package com.example.lurenjiaspring.config.guojihua;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Configuration
public class Demo {
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource result = new ResourceBundleMessageSource();
        //可以指定国际化化配置文件的位置，格式：路径/文件名称,注意不包含【语言_国 家.properties】含这部分
        result.setBasenames("i18n/message");
        result.setCacheMillis(1000);
       return result;
    }

    public static void main(String[] args) throws ParseException {
//        LocalDate startDate = LocalDate.of(2022, 1, 1);
//        LocalDate endDate = LocalDate.of(2022, 12, 31);
//
//        long months = startDate.until(endDate, ChronoUnit.MONTHS);
//        long days = startDate.until(endDate, ChronoUnit.DAYS);
//        System.out.println("startDate(2020.04.27)和endDate(2020.07.02)相差月数："+months);
//        System.out.println("startDate(2020.04.27)和endDate(2020.07.02)相差天数："+days);
//
//        LocalDateTime startTime = LocalDateTime.of(2020, 04, 27,18,20,10);
//        LocalDateTime endTime = LocalDateTime.of(2020, 04, 27,18,30,12);
//        long minutes = startTime.until(endTime, ChronoUnit.MINUTES);
//        System.out.println("startTime(2020.04.27 18:20:10)和endTime(2020.04.27 18:30:20)相差分钟数："+minutes); // * 原文章链接https://blog.csdn.net/qq_27471405/article/details/106824023 * 其他均为盗版，公众号：灵儿的笔记(zygxs
        int monthSpace = getMonthSpace("2022-1-1", "2022-12-31");
    }


    public static int getMonthSpace(String date1, String date2)
            throws ParseException {

        int result = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));

        int i = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        int month = 0;
        if (i < 0) {
            month = -i * 12;
        } else if (i > 0) {
            month = i * 12;
        }
        result = (c2.get(Calendar.MONDAY) - c1.get(Calendar.MONTH)) + month;

        return result == 0 ? 1 : Math.abs(result);

    }
}
