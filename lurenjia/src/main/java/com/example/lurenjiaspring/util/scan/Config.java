package com.example.lurenjiaspring.util.scan;


import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.*;


//MetadataReader接口
        //类元数据读取器，可以读取一个类上的任意信息，如类上面的注解信息、类的磁盘路径信息、类的class对象的各种信息，spring进行了封装，提供了各种方便使用的方法。
@ComponentScan(basePackages = {"com.example.lurenjiaspring.util.scan"},useDefaultFilters = true,includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM,classes = MyFilter.class)})
@Import(value = {StringUtils.class})
@Configuration
public class Config {

}