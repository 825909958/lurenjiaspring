package com.example.lurenjiaspring.util.fuctiondemo;

import com.example.lurenjiaspring.util.fuctiondemo.pojo.Entity1;
import com.example.lurenjiaspring.util.fuctiondemo.pojo.Entity2;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author THT  obj1.set(obj2.get())
 */
public class FieldForSetting {

    public static void main(String[] args) {
        Entity1 entity1 = new Entity1();
        Entity2 entity2 = new Entity2();
        System.out.println("entity1.getName() = " + entity1.getName());
        System.out.println("entity2.getName() = " + entity2.getName());
        Supplier<String> supplier2 = entity2::getName;
        Consumer<String> consumer1 = entity1::setName;
        //BiConsumer<Entity1, String> setName = Entity1::setName;
        //Function<Entity1, String> getName = Entity1::getName;
        setValue(consumer1,supplier2);
        System.out.println("entity1.getName() = " + entity1.getName());
        System.out.println("entity2.getName() = " + entity2.getName());


    }

    private static void setValue(Consumer<String> consumer,Supplier<String> function) {
        consumer.accept(function.get());
    }


 // 给entity1 设置 entity2的值
 //   private static String setValue(Entity1 entity1, BiConsumer<Entity1, String> consumer,Entity2 entity2,Function<Entity2, String> function) {
 //
 //       consumer.accept(entity1,function.apply());
 //   }

}
