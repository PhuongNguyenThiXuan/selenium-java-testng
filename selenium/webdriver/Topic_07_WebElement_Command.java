package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_07_WebElement_Command {
    WebDriver driver;

    @Parameters("")
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        System.out.println("Driver ID: " + driver.toString());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01(){
        //Mo URL bat ki
        driver.get("https://www.facebook.com/");
    }



    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
