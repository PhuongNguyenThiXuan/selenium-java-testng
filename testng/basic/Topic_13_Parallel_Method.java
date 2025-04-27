package basic;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_13_Parallel_Method {

    @Test
    public void register(){
        System.out.println("Register New Account");
        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = "register")
    public void login(){
        System.out.println("Login to System");
    }

    @Test(dependsOnMethods = "login")
    public void orderProduct(){
        System.out.println("Order Product");
    }

    @Test(dependsOnMethods = "orderProduct")
    public void pay(){
        System.out.println("Payment");
    }

    @Test(dependsOnMethods = "pay")
    public void ship(){
        System.out.println("Ship Product");
    }
}
