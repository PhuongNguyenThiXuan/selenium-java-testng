package javaTester;

import org.testng.annotations.Test;

public class Topic_02_Primitive_Reference {
    int x;

    @Test
    public void TC_01_Primitive_Type() {
        int i = 42;
        int copyi = i;
        System.out.print("i = " + i);
        System.out.print("copyi = " + copyi);
    }


    @Test
    public void Topic_02_Primitive_Reference() {
        Topic_02_Primitive_Reference c = new Topic_02_Primitive_Reference();
        c.x = 5;
        System.out.print("c.x = " + c.x + "|");

        Topic_02_Primitive_Reference d = new Topic_02_Primitive_Reference();
        System.out.print("d.x = " +d.x + "|");

        c.x = 10;
        d = c;
        System.out.print("c.x = " + c.x + "|");
        System.out.print("d.x = " +d.x + "|");

    }
}
