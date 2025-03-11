package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_24_Wait_PI_Element_Status {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_00_Static_Wait() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        //1. Click Start button
        driver.findElement(By.cssSelector("div#start button")).click();
        //2. Define a static wait (Thread.sleep)
        Thread.sleep(3000);
        //3. Wait the text displayed
        Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());
        //4. Verify the text displayed
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(),"Hello World!");
    }


    @Test
    public void TC_01_Visible_HTML(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        //1. Click Start button
        driver.findElement(By.cssSelector("div#start button")).click();
        //2. Define a explicitWait until text "Hello World!" is visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));
        //3. Verify the text displayed
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(),"Hello World!");
    }

    @Test
    public void TC_02_Invisible_HTML(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        //1. Click Start button
        driver.findElement(By.cssSelector("div#start button")).click();
        //2. Wait loading icon invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
        //3. Verify the text displayed
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(),"Hello World!");
    }

    @Test
    public void TC_03_Invisible_Not_HTML() throws InterruptedException {
        driver.get("https://tiki.vn/");
        Thread.sleep(2000);

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[type='tel']")));
    }

    @Test
    public void TC_04_Presence(){
        //ĐK: 100% có trong HTML, không quan tâm có hay không hiển thị trên UI
        driver.get("https://live.techpanda.org/");
        //1. Element có hiển thị và có trong html
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.footer a[title='My Account']")));
        //2. Element không hiển thị nhưng có trong html
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#header-account a[title='My Account']")));
    }

    @Test
    public void TC_05_Staleness() throws InterruptedException {
        //ĐK cần: Không có trong html và không có trên
        //ĐK đủ: Element thời điểm A có trong html nhưng thời điểm B không còn nữa
        driver.get("https://tiki.vn/");
        Thread.sleep(2000);
        //1. Click Account buttn
        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        //2. Get Phone element
        WebElement phone = driver.findElement(By.cssSelector("input[type='tel']"));
        //3. Click close popup
        driver.findElement(By.cssSelector("button.btn-close")).click();
        //4. Verify phone element is invisible in html
        explicitWait.until(ExpectedConditions.stalenessOf(phone)); //Bat buoc phai truyen element vao param
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
