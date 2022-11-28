package com.example.lurenjiaspring.util.reflectutil.enumutil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Locale;
import java.util.Map;

public class Demo1 {

    static void convertToAllInformation(Information information) {
        Map map = JSONObject.toJavaObject((JSON) JSONObject.toJSON(information), Map.class);
        AllInformation allInformation = new AllInformation();
        String name = ((String) map.get("name")).toLowerCase(Locale.ROOT);
        Enum anEnum = Enum.getEnum(name);
        allInformation.setName(name);
        allInformation.setWeight(anEnum.getWeight());
        allInformation.setAge(anEnum.getAge());
        allInformation.setHeight(anEnum.getHeight());
//            allInformation.setAge(((String)key).toUpperCase(Locale.ROOT));
//            allInformation.setHeight(((String)key).toUpperCase(Locale.ROOT));
//            allInformation.setWeight(((String)key).toUpperCase(Locale.ROOT));
        System.out.println(allInformation);
    }

    public static void main(String[] args) {
        convertToAllInformation(new Information("tht"));
    }
}
