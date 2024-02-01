package com.example.lurenjiaspring.util.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 不使用spring的加载配置的方式
 *
 * @author lucky
 * @create 2020/3/7
 * @since 1.0.0
 */
public class ConfigLoader {

    private static Properties prop = new Properties();
    public static final String DEFAULT_CONFIG_FILE_NAME = "a.properties";
    /**
     * 会根据window和Linux的不同来适配
     */
    public static final String SLASH = File.separator;

    static {
        loadProperties();
    }

    /**
     * 加载配置文件分为三个层次
     * 1.加载项目内置classpath:env.properties
     * 2.加载外部配置文件env.properties（会给定一个默认路径）
     * 3.加载JVM命令行参数
     */
    private static void loadProperties() {
        loadLocalProperties();
        //loadExtProperties();
        loadSystemEnvProperties();
    }

    /**
     * 加载JVM命令行参数、Environment参数
     */
    private static void loadSystemEnvProperties() {
        prop.putAll(System.getenv());
        prop.putAll(System.getProperties());
    }

    ///**
    // * 加载外部配置文件env.properties（会给定一个默认路径）
    // * 笔者所在公司，会根据不同的项目名，统一路径设置为
    // * /envconfig/{app.name}/env.properties
    // */
    //private static void loadExtProperties() {
    //    // 获取全路径
    //    // 所以需要首先在内部env.properties中配置上app.name
    //    if (prop.containsKey("app.name")) {
    //        String appName = prop.getProperty("app.name");
    //        String path = SLASH + "envconfig" + SLASH + appName + SLASH + DEFAULT_CONFIG_FILE_NAME;
    //
    //        Properties properties = ConfigUtil.loadProperties(path);
    //        if (null != properties) {
    //            prop.putAll(properties);
    //        }
    //    }
    //}


    /**
     * 对外提供的方法，获取配置信息
     * @param key key
     * @return 配置值
     */
    public static String getValue(String key) {
        return prop.getProperty(key);
    }


    /**
     * 加载项目内置classpath:env.properties
     */
    private static void loadLocalProperties() {
        //Properties properties = ConfigUtil.loadProperties(ConfigUtil.CLASSPATH_FILE_FLAG + DEFAULT_CONFIG_FILE_NAME);
        //if (null != properties) {
        //    prop.putAll(properties);
        //}
        InputStream in  = ConfigLoader.class.getClassLoader().getResourceAsStream("application-dev.yml");
        try {
            prop.load(in);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
