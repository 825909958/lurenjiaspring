package com.example.lurenjiaspring.util.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Demo {
    public static void main(String[] args) throws IOException {
        StringBuffer buffer = new StringBuffer();
        Process process = Runtime.getRuntime().exec("cmd /c route print");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }


    }
}
