package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

//Default_dropdown: Select => Option
public class Topic_11_Default_Dropdown {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void beforeClass(){
//        EdgeOptions edgeOptions = new EdgeOptions();
//        edgeOptions.addArguments("--user-data-dir=/Users/phuongntx2/Library/Application Support/Microsoft Edge");
//        edgeOptions.addArguments("--profile-directory=Profile 1");
//        driver = new EdgeDriver(edgeOptions);

        FirefoxOptions option = new FirefoxOptions();
        option.addPreference("geo.enabled", false);
        option.addPreference("geo.provider.use_corelocation", false);
        driver = new FirefoxDriver(option);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Nopcommerce(){
        String firstName = "Mia";
        String lastName = "Nguyen";
        String fullName = firstName + " " + lastName;
        String emailAddress = "pngtest" + new Random().nextInt(9999) + "@yopmail.com";
        String password = "123456a@A";
        String confirmation = "123456a@A";

        //1. Access url
        driver.get("https://demo.nopcommerce.com/");
        //2. Click Register
        driver.findElement(By.cssSelector("a.ico-register")).click();
        //3. Input valid data
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);
        driver.findElement(By.id("Email")).sendKeys(emailAddress);
        //Day = 1
        select = new Select(driver.findElement(By.name("DateOfBirthDay")));
        select.selectByVisibleText("1");
        //Month = May
        select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        select.selectByVisibleText("May");
        //Year = 1980
        select = new Select(driver.findElement(By.name("DateOfBirthYear")));
        select.selectByVisibleText("1980");
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(confirmation);
        //4. Click REGISTER
        driver.findElement(By.id("register-button")).click();
        //5. Verify validation text after register successfully
        Assert.assertEquals(driver.findElement(By.xpath("div.result")).getText(),"Your registration completed");
        //6. Click My Account to verify day/month/year
        driver.findElement(By.cssSelector("a.ico-account")).click();
        Assert.assertEquals(driver.findElement(By.name("DateOfBirthDay")).getAttribute("value"),"1");
        Assert.assertEquals(driver.findElement(By.name("DateOfBirthMonth")).getAttribute("value"),"May");
        Assert.assertEquals(driver.findElement(By.name("DateOfBirthYear")).getAttribute("value"),"1980");
    }

    @Test
    public void TC_02_Rode() throws InterruptedException {
        driver.get("https://rode.com/en/support/where-to-buy");
        //2. Select Country = Vietnam; City = Ho Chi Minh
        new Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText("Vietnam");
        Thread.sleep(3000);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select#country"))).getFirstSelectedOption().getText(),"Vietnam");
        driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("HO CHI MINH");
        //3. Click Search button
        driver.findElement(By.xpath("//button[text()='Search']")).click();
        //4. Verify 16 dealer
        List<WebElement> dealerBranches = driver.findElements(By.cssSelector("div.dealer_branch h4"));
        Assert.assertEquals(dealerBranches.size(),16);

        for (WebElement dealerName : dealerBranches){
            System.out.println(dealerName.getText());
        }
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
