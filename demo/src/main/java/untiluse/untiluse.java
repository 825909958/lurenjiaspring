package untiluse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class untiluse {
    /**
     * list中存在一样的名字，id全部改成一样的
     * @param args
     */
    public static void main(String[] args) {
        HashMap<String, List<Student>> map = new HashMap<>();
        List<Student> students = new ArrayList<Student>();
        students.add(new Student("1", "tht"));
        students.add(new Student("2","tht"));
        map.put("1", students);
        for (Map.Entry<String, List<Student>> stringListEntry : map.entrySet()) {
            System.out.println("stringListEntry = " + stringListEntry.getKey());
            System.out.println("stringListEntry = " + stringListEntry.getValue());
        }
        HashMap<String, List<Student>> tempMap = new HashMap<>();
        for (Map.Entry<String, List<Student>> listEntry : map.entrySet()) {
            for (Student student: listEntry.getValue()) {
                String name = student.getName();
                if (!tempMap.containsKey(student.getName())) {
                    ArrayList<Student> students1 = new ArrayList<>();
                    students1.add(student);
                    tempMap.put(name, students1);
                }else {
                    // 存在id不同但是名字相同的list
                    if (!tempMap.get(name).get(0).getId().equals(student.getId())) {
                        System.out.println("1111");
                    }
                    String id = tempMap.get(name).get(0).getId();
                    student.setId(id);
                    tempMap.get(name).add(student);
                }
            }
        }
        for (Map.Entry<String, List<Student>> stringListEntry : map.entrySet()) {
            System.out.println("stringListEntry = " + stringListEntry.getKey());
            System.out.println("stringListEntry = " + stringListEntry.getValue());
        }
    }
}
