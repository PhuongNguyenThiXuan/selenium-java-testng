package basic;

import org.testng.annotations.Test;

public class Topic_07_Description {
    //TestNG se chay theo alphabet, 0->9
    //Cach de test case chay theo thu tu:
    //1. Danh so theo priority
    //2. Define ten ham co STT/theo alphabet. Ex: TC_01



    @Test(description = "C14433 - Register New Account")
    public void register(){
        System.out.println("Register New Account");
    }

    @Test(description = "C14435 - Login to System")
    public void login(){
        System.out.println("Login to System");
    }

    @Test
    public void orderProduct(){
        System.out.println("Order Product");
    }

    @Test
    public void pay(){
        System.out.println("Payment");
    }

    @Test(description = "")
    public void ship(){
        System.out.println("Ship Product");
    }

}
