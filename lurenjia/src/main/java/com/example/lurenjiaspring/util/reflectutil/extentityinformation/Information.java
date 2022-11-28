package com.example.lurenjiaspring.util.reflectutil.enumutil;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author THT
 */
@AllArgsConstructor
@NoArgsConstructor
public class Information {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
