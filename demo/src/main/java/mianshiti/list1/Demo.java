package mianshiti.list1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Demo {
    @Test
    public void a() {
        LinkedList objects = new LinkedList<>();
        objects.add("A");
        //objects.add(1,"A");
        objects.add(2, "B");
        String o = (String) objects.get(0);
        System.out.println("o = " + o);
    }

    @Test
    public void test2() {
        ArrayList objects = new ArrayList<>();
        objects.add(new HashMap<>());
        objects.add(new ArrayList<>());
        HashMap<Object, Object> map = new HashMap<>();
        map.put("1", objects);
        map.put("2", new HashMap<>());
        System.out.println("objects = " + objects);
        System.out.println("map = " + map);
    }
}
