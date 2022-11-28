package jihe;

import java.util.HashMap;

public class MapTest {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put(null, "1");
        map.put(null, "2");
        map.put("2", "2");
        System.out.println("map = " + map);
    }
}
