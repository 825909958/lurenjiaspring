package com.example.lurenjiaspring.util.scan.classes.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RestfulClassInfo {
    private String className;
    private String methodName;
    private String parametersAssembly;
    private String urlName;

}
