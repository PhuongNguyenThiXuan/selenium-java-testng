package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_03_Relative_Locator {
    WebDriver driver;


    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Relative_Locator(){
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

        //information
        driver.findElement(By.xpath("//span[@class='male']"));
        driver.findElement(By.xpath("//span[@class='female']"));
        driver.findElement(By.xpath("//input[@id='FirstName']"));
        driver.findElement(By.xpath("//input[@id='LastName']"));
        driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"));
        driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"));
        driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"));
        driver.findElement(By.xpath("//input[@name='Email']"));

        driver.findElement(By.xpath("//input[@name='Company']"));

        driver.findElement(By.xpath("//input[@id='Newsletter']"));

        driver.findElement(By.xpath("//input[@id='Password']"));
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']"));

        driver.findElement(By.xpath("//button[@id='register-button']"));

    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
