package com.example.lurenjiaspring.util.scan;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


//MetadataReader接口
        //类元数据读取器，可以读取一个类上的任意信息，如类上面的注解信息、类的磁盘路径信息、类的class对象的各种信息，spring进行了封装，提供了各种方便使用的方法。
@ComponentScan(basePackages = {"org.slf4j.impl","com.example.lurenjiaspring.util.scan"},useDefaultFilters = true,includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM,classes = MyFilter.class)})
@Configuration
public class config {
}