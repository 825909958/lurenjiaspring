package com.example.lurenjiaspring.entity;

import com.example.lurenjiaspring.annotion.THT;
import com.example.lurenjiaspring.annotion.TXT;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author THT
 */
@THT
@TXT
public class AnnotationDemo {

    @THT("asdasda")
    private String name;

    @THT
    @TXT
    public String getName() {
        return name;
    }

    @TXT
    public void setName(@THT String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Class<AnnotationDemo> demoClass = AnnotationDemo.class;

        Annotation[] annotation =demoClass.getAnnotations();
        List<Annotation> collect = Arrays.stream(annotation).filter(Objects::nonNull)
                .filter(annotation1 -> annotation1.annotationType().equals(THT.class))
                .collect(Collectors.toList());
        System.out.println("collect = " + collect);

        Method[] methods = demoClass.getMethods();
        List<Method> methodList = Arrays.stream(methods)
                .filter(method -> method.getAnnotation(THT.class) != null)
                .collect(Collectors.toList());
        System.out.println("methodList = " + methodList);

        Field[] fields = demoClass.getDeclaredFields();
        List<THT> annotations = Arrays.stream(fields)
                .filter(field -> field.getAnnotation(THT.class) != null)
                .peek(field -> field.setAccessible(true))
                .map(AccessibleObject::getAnnotations)
                .flatMap(Arrays::stream)
                .filter(field->field instanceof THT)
                .map(field->(THT)field)
                .collect(Collectors.toList());
        System.out.println("fields = " + Arrays.toString(fields));
        String value = annotations.get(0).value();
        System.out.println("value = " + value);


    }

    public  String getNameDemo(Field name){
        return name.getName();
    }
}
