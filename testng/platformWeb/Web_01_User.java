package platformWeb;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Web_01_User {
    @BeforeClass
    public void beforeClass(){
        System.out.println("Before Class");
    }

    @Test(groups = {"platformWeb", "user"})
    public void Test_01_CreateUser(){
        System.out.println("Test Method 01");
    }
    @Test(groups = {"platformWeb", "user"})
    public void Test_02_EditUser(){
        System.out.println("Test Method 02");
    }
    @Test(groups = {"platformWeb", "user"})
    public void Test_03_ViewUser(){
        System.out.println("Test Method 03");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("After Class");
    }
}
