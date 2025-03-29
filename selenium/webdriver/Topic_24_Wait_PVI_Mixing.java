
package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_24_Wait_PVI_Mixing {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeMethod
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();


    }

    @Test
    public void TC_01_Only_Implicit() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.cssSelector("input#email"));
    }

    @Test
    public void TC_02_Only_Explicit() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
    }

    @Test
    public void TC_03_Implicit_EqualTo_Explicit_() {
        //Implicit > Explicit | Implicit < Explicit | Implicit = Explicit
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("Start:" + getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
        } catch (Exception e) {
            System.out.println("End:" + getDateTimeNow());
            throw new RuntimeException(e);
        }

        //Start:Sat Mar 29 17:24:47 ICT 2025
        //End:  Sat Mar 29 17:24:57 ICT 2025
        //=> Chạy hết 10s
    }

    @Test
    public void TC_03_Implicit_OverThan_Explicit() {
        //Implicit > Explicit | Implicit < Explicit | Implicit = Explicit
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("Start:" + getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
        } catch (Exception e) {
            System.out.println("End:" + getDateTimeNow());
            throw new RuntimeException(e);
        }

        //Start:Sat Mar 29 17:25:06 ICT 2025
        //End:  Sat Mar 29 17:25:26 ICT 2025
        //=> Chạy hết 20s
    }

    @Test
    public void TC_03_Implicit_LessThan_Explicit() {
        //Implicit > Explicit | Implicit < Explicit | Implicit = Explicit
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(12));

        System.out.println("Start:" + getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
        } catch (Exception e) {
            System.out.println("End:" + getDateTimeNow());
            throw new RuntimeException(e);
        }

        //Start:Sat Mar 29 17:25:35 ICT 2025
        //End:  Sat Mar 29 17:25:47 ICT 2025
        //=> Chạy hết 12s
    }

    @Test
    public void TC_04_Only_Explicit_By() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Lấy 10s của Explicit
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
    }

    @Test
    public void TC_04_Only_Explicit_WebElement() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Sẽ fail ở 0s do truyền theo WebElement
        //Mà WebElement phải set theo timeout của Implicit
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div#advice-required-entry-email"))));
    }



    public String getDateTimeNow() {
        Date date = new Date();
        return date.toString();
    }

    @AfterMethod
    public void afterClass(){
        driver.quit();
    }
}
