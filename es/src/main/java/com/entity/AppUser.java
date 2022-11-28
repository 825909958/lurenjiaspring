package com.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Arrays;

@Document(indexName = "app_user")
@Data
public class AppUser extends App {
    @Id
    @Field(index = true, store = true, type = FieldType.Integer)
    public Integer id;

    @Field(index = true, store = true, type = FieldType.Keyword)
    String name;

    @Field(index = true, store = true, type = FieldType.Text, analyzer = "ik_smart")
    String email;

    @Field(index = true, store = true, type = FieldType.Text, analyzer = "ik_smart")
    String phone;

    @Field(index = true, store = true, type = FieldType.Text)
    String gender;

    @Field(index = true, store = true, type = FieldType.Text)
    String password;

    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer age;

    public Integer parentAge() {
        return super.getAge();
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        AppUser appUser = new AppUser();
        Class<AppUser> appUserClass = AppUser.class;
//        java.lang.reflect.Field[] declaredFields = appUserClass.getDeclaredFields();
//        // 1.注解相当于类上的信息
//        Arrays.stream(declaredFields).forEach((item) -> {
//            System.out.println(item.getAnnotation(Field.class));
//        });

        // 2.资源定位符
//        URL resource = appUserClass.getResource("/com/entity");
//        String path = resource.getPath().substring(1,resource.getPath().length());
//        File file = new File(path + File.separator + "App.java");
//        System.out.println(file.isDirectory());
//        System.out.println(path);
//        System.out.println(resource);
        // 3.给父类字段赋值
        java.lang.reflect.Field[] declaredFields = appUserClass.getFields();
        Arrays.stream(declaredFields).forEach((item) -> {
            System.out.println(item.getName());
        });
        // getField优先获取父类的字段
//        java.lang.reflect.Field age1 = appUserClass.getField("age");
        // getDeclaredField只获取子类自己声明的字段
        java.lang.reflect.Field age = appUserClass.getDeclaredField("age");
//        age.set(appUser, 24);
        System.out.println(appUser.getAge());

        // 给父类跟子类字段重复的父类字段塞值
        java.lang.reflect.Field age1 = App.class.getField("age");
        age1.set(appUser, 24);
        System.out.println(appUser.getAge());
        System.out.println(appUser.parentAge());

    }
//    public static void main(String[] args) {
//        AppUser appUser = new AppUser();
///**
// * 类的函数
// */
//        BiConsumer<AppUser, Integer> setAge = AppUser::setAge;
//        setAge.accept(appUser, 2);
//
//        System.out.println(appUser);
//
//        Function<AppUser, Integer> getAge = AppUser::getAge;
//        System.out.println(getAge.apply(appUser));
//
//        /**
//         * 对象的函数       为什么所有函数不用函数表达式调用？？？？？？？？？？？
//         */
//        Consumer<Integer> setAgeSupplier = appUser::setAge;
//        Supplier<Integer> getAgeSupplier = appUser::getAge;
//    }
}
