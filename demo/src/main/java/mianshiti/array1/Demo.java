package mianshiti.array1;

public class Demo {
    public static void main(String[] args) {
        String[][] strings = new String[3][2];
        strings[0][0]="abc";
        strings[0][1]="12345";
        strings[1][0]="12345";
        strings[1][1]="12345";
        strings[2][0]="12345";
        strings[2][1]="12345";
        System.out.println("strings.length = " + strings.length);
        System.out.println("strings[0][1].length = " + strings[0][1].length());
        System.out.println("strings[0][0].length = " + strings[0][0].length());
        System.out.println("strings[1][0].length = " + strings[1][0].length());
        System.out.println("strings[1][1].length = " + strings[1][1].length());
        System.out.println("strings[2][0].length = " + strings[2][0].length());
        System.out.println("strings[2][1].length = " + strings[2][1].length());
    }
}
