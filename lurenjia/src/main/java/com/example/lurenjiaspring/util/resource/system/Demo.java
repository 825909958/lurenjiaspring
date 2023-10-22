package com.example.lurenjiaspring.util.resource.system;

import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {

    @Test
    public void getPath() {
        String property = System.getProperty("user.dir");
        String replace = property.replace(";", "\n");
        System.out.println("replace = " + replace);
    }

    @Test
    public void getPath2() throws IOException {

    }

    @Test
    public void getPath3() {
        String classpath = System.getProperty("java.class.path");
        String replace = classpath.replace(";", "\n");
        System.out.println("Classpath: " + replace);
    }

    /**
     * 通过file:读取文件
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //读取这层目录下的文件
        // Resource[] resources = resolver.getResources("classpath:com/example/lurenjiaspring/*.class");
         Resource[] resources = resolver.getResources("classpath:/*.xml");
        // 注意项目不是根目录要带上lurenjia这个路劲，坑
        //Resource[] resources = resolver.getResources("file:lurenjia/target/classes/*.xml");
        Arrays.stream(resources).forEach(i->{
            System.out.println("i.getFilename() = " + i.getFilename());
        });

        List<String> strings = new ArrayList<>();
        strings.remove("1");
        System.out.println("strings = " + strings);
    }
}
