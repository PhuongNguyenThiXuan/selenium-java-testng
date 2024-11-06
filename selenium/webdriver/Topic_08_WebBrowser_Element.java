package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_08_WebBrowser_Element {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Page_URL(){
        driver.get("https://live.techpanda.org/");
        //1. Click My Account at footer
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        //2. Get URL
        driver.getCurrentUrl().equals("https://live.techpanda.org/index.php/customer/account/login/");
        //3. Click Create An Account
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        //4. Get URL
        driver.getCurrentUrl().equals("https://live.techpanda.org/index.php/customer/account/create/");
    }


    @Test
    public void TC_02_Page_Title(){
        driver.get("https://live.techpanda.org/");
        //1. Click My Account at footer
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        //2. Get Title
        driver.getTitle().equals("Customer Login");
        //3. Click Create An Account
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        //4. Get Title
        driver.getTitle().equals("Create New Customer Account");

    }

    @Test
    public void TC_03_Page_Navigation(){
        driver.get("https://live.techpanda.org/");
        //1. Click My Account at footer
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        //2. Click Create An Account
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        //3. Verify url current page
        driver.getCurrentUrl().equals("https://live.techpanda.org/index.php/customer/account/create/");
        //4. Back to login page
        driver.navigate().back();
        //5. Verify url current page
        driver.getCurrentUrl().equals("https://live.techpanda.org/index.php/customer/account/login/");
        //6. Forward to register
        driver.navigate().forward();
        //7. Verify title
        driver.getTitle().equals("Create New Customer Account");
    }

    @Test
    public void TC_04_Get_PageSource(){
        driver.get("https://live.techpanda.org/");
        //1. Click My Account at footer
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        //2. Get PageSource
        driver.getPageSource().equals("Login or Create an Account");
        //3. Click Create An Account
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        //4. Get PageSource
        driver.getPageSource().equals("Create an Account");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
