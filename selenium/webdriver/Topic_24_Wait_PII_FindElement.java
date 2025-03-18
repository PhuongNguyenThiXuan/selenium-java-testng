package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_24_Wait_PII_FindElement {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
    }

    @Test
    public void TC_01_FindElement(){
        driver.findElement(By.cssSelector("input#email"));

    }


    @Test
    public void TC_02_FindElements(){
        //FindElement trong list nhieu Element => lay Element dau tien
//        driver.findElement(By.cssSelector("input:not([type='hidden'])")).sendKeys("Test");
        //FindElements trong list nhieu Element => lay Element dau tien
//        driver.findElements(By.cssSelector("input:not([type='hidden'])"));



        //Tìm thấy 1 element => se dung lai ngay khi tim thay element3.6.1-vn-android
//        List<WebElement> elementList = driver.findElements(By.cssSelector("input#email"));
//        System.out.println(elementList.size());

        //Tìm thấy 0 element => chay het 10s
//        List<WebElement> elementList1 = driver.findElements(By.cssSelector("input#email1"));
//        System.out.println(elementList1.size());
//        Assert.assertEquals(elementList1.size(),0);

        //Tìm thấy x elements
        List<WebElement> elementList = driver.findElements(By.cssSelector("input:not([type='hidden'])"));
        elementList.get(0).sendKeys("1");
        elementList.get(3).sendKeys("4");

        for(WebElement index:elementList){
            index.clear();
            index.sendKeys("this is a test");
        }
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
