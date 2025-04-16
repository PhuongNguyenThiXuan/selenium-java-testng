package basic;

import org.testng.annotations.*;

public class Topic_01_Annotations {
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Before Suite");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("Before Test");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("Before Class");
    }
    @BeforeMethod //chay voi moi @Test
    public void beforeMethod(){
        System.out.println("Before Method");
    }



    @AfterSuite
    public void afterSuite(){
        System.out.println("After Suite");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("After Test");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("After Class");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("After Method");
    }


    @Test
    public void Test_01(){
        System.out.println("Test Method 01");
    }
    @Test
    public void Test_02(){
        System.out.println("Test Method 02");
    }
    @Test
    public void Test_03(){
        System.out.println("Test Method 03");
    }

}
