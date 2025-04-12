package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_24_Wait_PVIII_PageReady {
    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait fluentWait;
    FluentWait<WebDriver> fluentWaitDriver;
    FluentWait<WebElement> fluentWaitElement;
    FluentWait<Boolean> fluentWaitBoolean;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        //Khởi tạo
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Element_Invisible(){
        driver.get("https://api.orangehrm.com/");

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner")));
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#project h1"),"OrangeHRM REST API Documentation"));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(),"OrangeHRM REST API Documentation");

    }

    @Test
    public void TC_02_All_Element_Invisible(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        //1. Login
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Assert.assertTrue(isAllLoadingSpinnerInvisible());

        //2. Select PIM tab
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']"))).click();
        Assert.assertTrue(isAllLoadingSpinnerInvisible());

        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span.oxd-topbar-header-breadcrumb h6"),"PIM"));
        Assert.assertEquals(driver.findElement(By.cssSelector("span.oxd-topbar-header-breadcrumb h6")).getText(),"PIM");

        //3. Select Time tab
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Time']"))).click();
        Assert.assertTrue(isAllLoadingSpinnerInvisible());

        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span.oxd-topbar-header-breadcrumb h6"),"Time"));
        Assert.assertEquals(driver.findElement(By.cssSelector("span.oxd-topbar-header-breadcrumb h6")).getText(),"Time");
    }

    @Test
    public void TC_03_Page_Ready(){
        driver.get("https://admin-demo.nopcommerce.com");
        //1. Login
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='Email']"))).clear();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='Password']"))).clear();
        driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input[name='Password']")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Assert.assertTrue(isPageLoadedSuccess());

        //2.
        driver.get("https://admin-demo.nopcommerce.com/Admin/Product/List");
        Assert.assertTrue(isPageLoadedSuccess());

        //3.
        driver.get("https://admin-demo.nopcommerce.com/Admin/Order/List");
        Assert.assertTrue(isPageLoadedSuccess());

        //4.
        driver.get("https://admin-demo.nopcommerce.com/Admin/Customer/List");
        Assert.assertTrue(isPageLoadedSuccess());

        //5.
        driver.get("https://admin-demo.nopcommerce.com/Admin");
        Assert.assertTrue(isPageLoadedSuccess());
    }

    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public boolean isAllLoadingSpinnerInvisible (){
        return explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner"))));
    }

    @AfterClass
    public void afterClass(){
        //driver.quit();
    }
}
