package javaTester;

import java.util.ArrayList;

public class Topic_06_Generic {
    public static void main(String[] args){
        //Non-Generic (khong bi rang buoc kieu du lieu)
        ArrayList student = new ArrayList<>();
        student.add("Nguyen Van A");
        student.add(18);

        //Generic <Type>
        ArrayList<String> students = new ArrayList<>();
        students.add("Nguyen Van A");
        //students.add(18); // => truyen 18 khong phai String nen se bao loi ngay
    }
}
