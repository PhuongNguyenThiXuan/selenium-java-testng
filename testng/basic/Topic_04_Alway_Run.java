package basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Alway_Run {
    //Arrange
    //Set up/ Initial Data/ Browser/ Driver/ Variable/ DB....
    WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.get("https://www.google.com.vn/");
        driver.manage().window().maximize();

        System.out.println("Passed Before Class"); //Before class không passed => Test case/After class sẽ không chạy: Nên sẽ phải add AlwayRun
    }

    @Test
    public void TC_01(){
        System.out.println("Action and verify");

    }

    @AfterClass
    public void afterClass(){
        System.out.println("Passed After Class");
    }

}
