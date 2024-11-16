package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_09_Login_Exercise {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Login_With_Empty_Email_Password(){
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.xpath("//span[text()='Login']")).click();

        if(driver.findElement(By.cssSelector("div#advice-required-entry-email")).isDisplayed()){
            System.out.println(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText());
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),"This is a required field.");

        if(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).isDisplayed()){
            System.out.println(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText());
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(),"This is a required field.");
    }



    @Test
    public void TC_02_Login_With_Invalid_Email(){
        driver.get("https://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("123434234@12312.123123");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("12345");
        driver.findElement(By.xpath("//span[text()='Login']")).click();

        if(driver.findElement(By.cssSelector("div#advice-validate-email-email")).isDisplayed()){
            System.out.println(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText());
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");

    }

    @Test
    public void TC_03_Login_With_Password_Over_6_Chars(){
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123");
        driver.findElement(By.xpath("//span[text()='Login']")).click();

        if(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).isDisplayed()){
            System.out.println(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText());
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");

    }

    @Test
    public void TC_04_Login_With_Incorrect_Email_Password() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123123123");
        driver.findElement(By.xpath("//span[text()='Login']")).click();

        if(driver.findElement(By.cssSelector("li.error-msg")).isDisplayed()){
            System.out.println(driver.findElement(By.cssSelector("li.error-msg")).getText());
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg")).getText(),"Invalid login or password.");


    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
