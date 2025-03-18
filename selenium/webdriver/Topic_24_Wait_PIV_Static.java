package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_24_Wait_PIV_Static {
    WebDriver driver;

    @BeforeMethod
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().window().maximize();

        driver.get("https://automationfc.github.io/dynamic-loading/");
    }

    @Test(description = "Thời gian implicit bằng 0")
    public void TC_01() {
        //1. Click to START button
        driver.findElement(By.cssSelector("div#start button")).click();
        //Loading will be appear after 5s
        sleepInSecond(0);
        //2. Verify text: "Hello World!"
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }


    @Test(description = "Thời gian implicit ngắn hơn thời gian element xuất hiện")
    public void TC_02() {
        //1. Click to START button
        driver.findElement(By.cssSelector("div#start button")).click();
        //Loading will be appear after 5s
        sleepInSecond(3);
        //2. Verify text: "Hello World!"
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }

    @Test(description = "Thời gian implicit bằng thời gian element xuất hiện")
    public void TC_03() {
        //1. Click to START button
        driver.findElement(By.cssSelector("div#start button")).click();
        //Loading will be appear after 5s
        sleepInSecond(5);
        //2. Verify text: "Hello World!"
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }

    @Test(description = "Thời gian implicit dài hơn thời gian element xuất hiện")
    public void TC_04() {
        //1. Click to START button
        driver.findElement(By.cssSelector("div#start button")).click();
        //Loading will be appear after 5s
        sleepInSecond(20);
        //2. Verify text: "Hello World!"
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }

    public void sleepInSecond (long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterMethod
    public void afterClass(){
        driver.quit();
    }
}
