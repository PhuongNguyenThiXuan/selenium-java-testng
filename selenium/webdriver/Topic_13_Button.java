package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_13_Button {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Button() throws InterruptedException {
        driver.get("https://www.fahasa.com/customer/account/create");
        By loginButton = By.cssSelector("button.fhs-btn-login");
        String backgroundLogin = driver.findElement(loginButton).getCssValue("background-color");

        //2. Navigate to Register tab
        driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();

        //3. Verify Login button is disable
        new WebDriverWait(driver,Duration.ofSeconds(10)).
                until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(loginButton)));
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());
        Assert.assertEquals(driver.findElement(loginButton).getText().trim(),"Đăng nhập");

        //4. Verify Login button have background color is grey
        Assert.assertEquals(Color.fromString(backgroundLogin).asHex().toUpperCase(),"#000000");

        //5+6. Verify Login is enable after input data for Email/Password field
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("test@yopmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());

        //7. Verify Login button have background color is red
        Assert.assertEquals(Color.fromString(backgroundLogin).asHex().toUpperCase(),"#C92127");
    }


    @AfterClass
    public void afterClass(){
        //driver.quit();
    }
}
