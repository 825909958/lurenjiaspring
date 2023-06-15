package io.file;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Demo {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\");

        File[] files = file.listFiles();
        Arrays.stream(files).forEach(System.out::println);
    }
}
