package mianshiti.list1;

import mianshiti.entity.PicTure;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /**
     * list多个排序
     */
    @Test
    public void test3() {
        PicTure picTure1 = new PicTure();
        PicTure picTure2 = new PicTure();
        PicTure picTure3 = new PicTure();
        List<PicTure> collect = Stream.of(picTure1, picTure2, picTure3).collect(Collectors.toList());
        List<PicTure> picTures = collect.stream().sorted(Comparator.comparing(PicTure::getName).
                thenComparing(PicTure::getUrl)).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(picTures);
    }

    @Test
    public void test4() {
        ArrayList<String> strings = null;
        for (String string : strings) {
            System.out.println("string = " + string);
        }
    }
}
