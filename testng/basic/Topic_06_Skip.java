package basic;

import org.testng.annotations.Test;

public class Topic_06_Skip {
    //TestNG se chay theo alphabet, 0->9
    //Cach de test case chay theo thu tu:
    //1. Danh so theo priority
    //2. Define ten ham co STT/theo alphabet. Ex: TC_01



    @Test(enabled = false)
    public void register(){
        System.out.println("Register New Account");
    }

    @Test
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

    //@Test(enabled = false) => nếu 1 hàm không có annotations sẽ không được hiểu là 1 test case => sẽ không chạy
    public void ship(){
        System.out.println("Ship Product");
    }

}
