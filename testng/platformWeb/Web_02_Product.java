package platformWeb;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Web_02_Product {
    @BeforeClass
    public void beforeClass(){
        System.out.println("Before Class");
    }

    @Test(groups = {"platformWeb", "product"})
    public void Product_01_CreateUser(){
        System.out.println("Product Create User");
    }
    @Test(groups = {"platformWeb", "product"})
    public void Product_02_EditUser(){
        System.out.println("Product Edit User");
    }
    @Test(groups = {"platformWeb", "product"})
    public void Product_03_ViewUser(){
        System.out.println("Product View User");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("After Class");
    }
}
