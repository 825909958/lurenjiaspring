package com.example.lurenjiaspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author THT
 */
@Configuration //如果去掉Configuration，则调用3次a
public class ConfigurationVerify {
    @Bean
    public Verify a(){
//        System.out.println("aaa");
        Verify verify = new Verify();
//        System.out.println("verify = " + verify);
        return verify;
    }

    @Bean
    public Verify b(){
        Verify a = this.a();
//        System.out.println(a);
        return a;
    }

    @Bean
    public Verify c(){
        Verify a = this.a();
//        System.out.println(a);
        return a;
    }

}
