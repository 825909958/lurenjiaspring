package com.example.lurenjiaspring.factory;

import java.util.Map;
import java.util.Optional;

/**
 * @author THT
 */
public class FactoryDemo {
   static Map<String, Type> MAP;
    static {
        MAP.put("TypeA", new TypeA());
        MAP.put("TypeB", new TypeB());
    }

    public Type getTypeByFactory(String type){
        return Optional.ofNullable(MAP.get(type)).orElse(null);
    }
}
