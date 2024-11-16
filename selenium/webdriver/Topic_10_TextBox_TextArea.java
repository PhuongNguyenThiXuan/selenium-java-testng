package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_10_TextBox_TextArea {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01(){
        //Step 1: Get url
        driver.get("https://live.techpanda.org/");
        //Step 2: Click "My Account"
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        //Step 3: Click "CREATE AN ACCOUNT"
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        //Step 4: Input information
        String firstName = "Mia";
        String lastName = "Nguyen";
        String fullName = firstName + " " + lastName;
        String emailAddress = "pngtest" + new Random().nextInt(9999) + "@yopmail.com";
        String password = "123456a@A";
        String confirmation = "123456a@A";
        String thoughts = "This is a test\rThis is a test\rThis is a test";
        String review = "Good";
        String nickname = "Automation";

        driver.findElement(By.id("firstname")).sendKeys(firstName);
        driver.findElement(By.id("lastname")).sendKeys(lastName);
        driver.findElement(By.id("email_address")).sendKeys(emailAddress);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("confirmation")).sendKeys(confirmation);
        //Step 5: Click "REGISTER"
        driver.findElement(By.cssSelector("button[title='Register']")).click();
        driver.switchTo().alert().accept();
        //Step 6: Verify message
        Assert.assertEquals(driver.findElement(By.cssSelector("ul[class='messages']")).getText(),"Thank you for registering with Main Website Store.");
        //Step 7: Get contact info
        String contactInformation = driver.findElement(By.xpath("//h3[contains(text(),'Contact Information')]/parent::div//following-sibling::div/p")).getText();
        Assert.assertTrue(contactInformation.contains(fullName));
        Assert.assertTrue(contactInformation.contains(emailAddress));
        //Step 8: Navigate to MOBILE tab
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        //Step 9: Select Samsung Galaxy
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']")).click();
        //Step 10: Click Ad Your Review
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();
        //Step 11: Input valid info and click Submit
        driver.findElement(By.id("Quality 1_5")).click();
        driver.findElement(By.cssSelector("textarea#review_field")).sendKeys(thoughts);
        driver.findElement(By.cssSelector("input#summary_field")).sendKeys(review);
        driver.findElement(By.cssSelector("input#nickname_field")).sendKeys(nickname);
        driver.findElement(By.cssSelector("button[title='Submit Review']")).click();
        driver.switchTo().alert().accept();

        //Step 12: Verify message
        Assert.assertEquals(driver.findElement(By.cssSelector("ul[class='messages']")).getText(),"Your review has been accepted for moderation.");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
