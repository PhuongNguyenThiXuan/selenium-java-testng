package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_24_Wait_PIII_Implicit {
    WebDriver driver;

    @BeforeMethod
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().window().maximize();

        driver.get("https://automationfc.github.io/dynamic-loading/");
    }

    @Test(description = "Thời gian implicit bằng 0")
    public void TC_01(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        //1. Click to START button
        driver.findElement(By.cssSelector("div#start button")).click();
        //Loading will be appear after 5s
        //2. Verify text: "Hello World!"
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }


    @Test(description = "Thời gian implicit ngắn hơn thời gian element xuất hiện")
    public void TC_02(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        //1. Click to START button
        driver.findElement(By.cssSelector("div#start button")).click();
        //Loading will be appear after 5s
        //2. Verify text: "Hello World!"
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }

    @Test(description = "Thời gian implicit bằng thời gian element xuất hiện")
    public void TC_03(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //1. Click to START button
        driver.findElement(By.cssSelector("div#start button")).click();
        //Loading will be appear after 5s
        //2. Verify text: "Hello World!"
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }

    @Test(description = "Thời gian implicit dài hơn thời gian element xuất hiện")
    public void TC_04(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //1. Click to START button
        driver.findElement(By.cssSelector("div#start button")).click();
        //Loading will be appear after 5s
        //2. Verify text: "Hello World!"
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }

    @AfterMethod
    public void afterClass(){
        driver.quit();
    }
}
