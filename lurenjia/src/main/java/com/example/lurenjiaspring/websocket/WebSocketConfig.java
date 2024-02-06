package com.example.lurenjiaspring.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    public static void main(String[] args) {
        // 字符串转两位小数
//        String a = "1.00";
//        DecimalFormat decimalFormat = new DecimalFormat("###.##%");
//
//        String format = decimalFormat.format(Double.parseDouble(a)/100);
//        System.out.println("format  = " + format);
//
//        String b = "22222222.93";
//        DecimalFormat df = new DecimalFormat("0.00%");
//        df.setRoundingMode(RoundingMode.HALF_UP);
//        String format2 = df.format(BigDecimal.valueOf(Double.parseDouble(b)));
//        System.out.println("format2 = " + format2);

        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(2);
        double num = 1.00;
        String percentage = nf.format(num);
        System.out.println("percentage = " + percentage);

        String s = "1.00" + "%";
        System.out.println("s = " + s);

    }
}
