package com.example.lurenjiaspring.util.reflectutil.extentityinformation;

/**
 * @author THT
 */

public enum Enum {
    /**
     *
     */
    THT("tht", "24", "男", "177", "160"),
    /**
     *
     */
    TXT("txt", "17", "男", "175", "115");

    Enum(String name, String age, String sex, String height, String weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    String name;

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    String age;
    String sex;
    String height;
    String weight;

    public static Enum getEnum(String name) {
        for (Enum value : Enum.values()) {
            if (value.getName().equals(name)) {
                return value;
            }
        }
        return null;
    }
}
