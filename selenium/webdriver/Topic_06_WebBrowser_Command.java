package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_06_WebBrowser_Command {
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

        //Dong broswer, khong quan tam co bao nhieu tab hoac window
        //driver.quit();

        //close thang tab current
        //driver.close();

        //tim element vs locator la tham so truyen vao
        //driver.findElement(By.cssSelector(""));

        //tim nhieu element vs locator la tham so truyen vao
        //driver.findElements(By.cssSelector(""));

        //lay url page hien tai
        driver.getCurrentUrl();
        //get current url va luu tru => goi lai o nhung lan sau (1)
        String getlink = driver.getCurrentUrl();
        driver.get(getlink); //mo url
        Assert.assertEquals(getlink,"https://www.facebook.com/"); //so sanh
        //get url va su dung ngay luc do (2)
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/");

        //get title o page hien tai
        driver.getTitle();
        System.out.println("WindowID: " + driver.getTitle());

        //lay windowID
        driver.getWindowHandle(); //1 cai - cua tab hien tai
        System.out.println("WindowID: " + driver.getWindowHandle());
        driver.getWindowHandles(); //tat ca

        //lay src cua pay hien tai
        driver.getPageSource();
        System.out.println("Page Source: " + driver.getPageSource());

        //Alert - Frame/iFrame - Window
        driver.switchTo().alert();

        driver.switchTo().frame(""); //switch vao frame
        driver.switchTo().defaultContent(); //=>switch tu frame ra page cha
        driver.switchTo().parentFrame(); //=>switch tu frame con ra frame cha

        driver.switchTo().window("");//switch qua window theo window ID
        driver.switchTo().newWindow(WindowType.TAB).get("https://www.facebook.com/login/"); //switch qua window moi theo url da truyen
        driver.switchTo().newWindow(WindowType.WINDOW).get("https://www.facebook.com/login/"); //selenium version 4 tro len

        driver.manage();





    }



    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
