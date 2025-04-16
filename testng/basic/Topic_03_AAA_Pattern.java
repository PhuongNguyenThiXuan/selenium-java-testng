package basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_AAA_Pattern {
    //Arrange
    //Set up/ Initial Data/ Browser/ Driver/ Variable/ DB....
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.get("");
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01(){
        //Action: Open > Click

        //Assert: Verify click successfully

    }

}
