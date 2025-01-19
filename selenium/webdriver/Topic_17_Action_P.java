package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_17_Action_P {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void beforeClass(){

//        EdgeOptions edgeOptions = new EdgeOptions();
//        edgeOptions.addArguments("--user-data-dir=/Users/phuongntx2/Library/Application Support/Microsoft Edge");
//        edgeOptions.addArguments("--profile-directory=Profile 1");
//        driver = new EdgeDriver(edgeOptions);
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        action = new Actions(driver);
    }

    @Test
    public void TC_01_Hover(){
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        //1. Hold on textbox
        action.moveToElement(driver.findElement(By.cssSelector("input#age")))
                .pause(Duration.ofSeconds(1))
                .perform();
        //2. Verify the contain of tooltip
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).isDisplayed());
    }


    @Test
    public void TC_02_Myntra(){
        driver.get("https://www.myntra.com/");
        //1. Hold on KIDS
        action.moveToElement(driver.findElement(By.xpath("//a[text()='Kids']/parent::div[@class='desktop-navLink']")))
                .pause(Duration.ofSeconds(1))
                .perform();
        //2. Select Home & Bath
        action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']")))
                .perform();
        //3. Verify navigation to Home & Bath page correctly
        Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(),
                "Kids Home Bath");

    }

    @Test
    public void TC_03_Fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/");
        Thread.sleep(9000);
        //1. Hover on any section in left menu
        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu")))
                .pause(Duration.ofSeconds(1))
                .perform();
        //2. Select in left menu
        action.moveToElement(driver.findElement(By.xpath("//ul[@class='nav navbar-nav verticalmenu']//span[text()='VPP - Dụng Cụ Học Sinh']")))
                .pause(Duration.ofSeconds(1))
                .perform();
        //3. Select "Got but chi"
        action.click(driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//ul[@class='nav-links']//li/a[text()='Gọt Bút Chì']")))
                .pause(Duration.ofSeconds(2))
                .perform();
        //4. Verify navigation to correctly 'Got but chi' page
        System.out.println(driver.findElement((By.cssSelector("ol.breadcrumb"))).getText());
        Assert.assertTrue(driver.findElement(By.cssSelector("ol.breadcrumb")).getText().contains("GỌT BÚT CHÌ"));

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
