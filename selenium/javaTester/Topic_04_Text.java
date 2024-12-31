package javaTester;

import java.util.Random;

public class Topic_04_Text {
    public static void main(String[] agrs){
        String authenLink = "http://the-internet.herokuapp.com/basic_auth";

        String username = "admin";
        String password = "admin";

        String[] text = authenLink.split("//");
        System.out.println(text[0]);
        System.out.println(text[1]);

        authenLink = text[0] + "//" + username + ":" + password + "@" + text[1];
        System.out.println(authenLink);
    }
}
