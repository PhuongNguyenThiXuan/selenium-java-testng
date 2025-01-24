package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_18_Fixed_Popup {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        //driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_NgoaiNgu24h_Fixed_Not_Found_In_DOM() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");
        Thread.sleep(2000);
        //1. Click Login button
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        //2. Verify Login popup is displayed
        By loginDialog = By.xpath("//div[@role='dialog']");
        Assert.assertTrue(driver.findElement(loginDialog).isDisplayed());
        //3. Input username/password info
        driver.findElement(By.xpath("//input[@placeholder='Tài khoản đăng nhập']")).sendKeys("pngtest");
        driver.findElement(By.xpath("//input[@placeholder='Mật khẩu']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[contains(@class,'dialog-button') and text()='Đăng nhập']")).click();
        //4. Verify message displayed
        Assert.assertEquals(driver.findElement(By.cssSelector("div#notistack-snackbar")).getText(),"Bạn đã nhập sai tài khoản hoặc mật khẩu!");
        //5. Close popup
        driver.findElement(By.cssSelector("button.close-btn")).click();
        Thread.sleep(3000);
        //6. Verify message is not exist
        Assert.assertEquals(driver.findElements(loginDialog).size(),0);
    }


    @Test
    public void TC_02_KyNangEnglish() throws InterruptedException {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");
        //1. Verify Login popup is displayed
        Assert.assertTrue(driver.findElement(By.cssSelector("div.k-popup-account-mb-content")).isDisplayed());
        //2. Input information for: username/password
        driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
        //3. Click Login button
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        Thread.sleep(3000);
        //4. Verify message is displayed
        Assert.assertTrue(driver.findElement(By.cssSelector("div#password-form-login-message")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");
    }

    @Test
    public void TC_03_Tiki() throws InterruptedException {
        driver.get("https://tiki.vn/");
        //1. Click on Login/ SignIn button
        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        Thread.sleep(2000);
        By loginDialog = By.cssSelector("div.ReactModalPortal div[role='dialog']");
        //2. Verify popup is displayed
        Assert.assertTrue(driver.findElement(loginDialog).isDisplayed());
        //3. Click on link: "Login with email"
        driver.findElement(By.cssSelector("p.login-with-email")).click();
        //4. Do not input information, click on Login button
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        //5. Verify the error message displayed under each field
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']//parent::div/following-sibling::span[1]")).getText(),
                "Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='password']//parent::div/following-sibling::span[1]")).getText(),
                "Mật khẩu không được để trống");
        //6. Close dialog
        driver.findElement(By.cssSelector("div.ReactModalPortal button.btn-close")).click();
        Thread.sleep(2000);
        //7. Verify dialog is disappeared
        Assert.assertEquals(driver.findElements(loginDialog).size(),0);

    }

    @AfterClass
    public void afterClass(){
        //driver.quit();
    }
}
