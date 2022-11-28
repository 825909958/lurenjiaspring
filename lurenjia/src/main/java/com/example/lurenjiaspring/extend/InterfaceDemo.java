package com.example.lurenjiaspring.extend;

/**
 * @author THT
 */
public interface InterfaceDemo {
    /**
     * @return string
     */
    default public String c(){
        return "default";
    }
}
