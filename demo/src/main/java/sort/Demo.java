package sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("A1bcd");
        strings.add("Abcd");
        strings.add("C");
        strings.add("D");
        System.out.println("strings = " + strings);
        List<String> collect = strings.stream().sorted().collect(Collectors.toList());
        System.out.println("strings1 = " + strings);
        System.out.println("collect = " + collect);



    }
}
