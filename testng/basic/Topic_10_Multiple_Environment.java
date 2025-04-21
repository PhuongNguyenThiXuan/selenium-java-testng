package basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_10_Multiple_Environment {
    WebDriver driver;
    String environmentURL;

    @Parameters({"browser", "environmentName"}) //truyen name cua parameter
    @BeforeClass
    public void beforeClass(String browserName, String environmentName){
        System.out.println("Browser Name: " + browserName);
        System.out.println("Environment Name: " + environmentName);
        switch (browserName.toUpperCase()){
            case "FIREFOX":
                driver = new FirefoxDriver();
                break;
            case "CHROME":
                driver = new ChromeDriver();
                break;
            case "EDGE":
                driver = new EdgeDriver();
                break;
            case "SAFARI":
                driver = new SafariDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not supported");
        }

        switch (environmentName.toUpperCase()){
            case "DEV":
                environmentURL = "https://dev.fahasa.com/";
                break;
            case "TEST":
                environmentURL = "https://test.fahasa.com/";
                break;
            case "PROD":
                environmentURL = "https://www.fahasa.com/";
                break;
            default:
                throw new RuntimeException("Environment name is not supported");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        //driver.manage().window().maximize();
        driver.get(environmentURL + "customer/account/create");
    }

    @Test
    public void TC_01_Button() throws InterruptedException {

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
        //Assert.assertEquals(Color.fromString(backgroundLogin).asHex().toUpperCase(),"#C92127");
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
