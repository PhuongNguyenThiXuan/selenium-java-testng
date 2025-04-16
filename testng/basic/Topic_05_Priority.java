package basic;

import org.testng.annotations.Test;

public class Topic_05_Priority {
    //TestNG se chay theo alphabet, 0->9
    //Cach de test case chay theo thu tu:
    //1. Danh so theo priority
    //2. Define ten ham co STT/theo alphabet. Ex: TC_01



    @Test(priority = 1)
    public void register(){
        System.out.println("Register New Account");
    }

    @Test(priority = 2)
    public void login(){
        System.out.println("Login to System");
    }

    @Test(priority = 3)
    public void orderProduct(){
        System.out.println("Order Product");
    }

    @Test(priority = 4)
    public void pay(){
        System.out.println("Payment");
    }

    @Test(priority = 5)
    public void ship(){
        System.out.println("Ship Product");
    }

}
