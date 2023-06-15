package jihe;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        for (Map.Entry<String,String> e :
                map.entrySet()) {
            System.out.println("e = " + e);
        }
        map.put(null, "1");
        map.put(null, "2");
        map.put("2", "2");
        for (Map.Entry<String,String> e :
                map.entrySet()) {
            System.out.println("e = " + e);
        }
        System.out.println("map = " + map);

    }
}
