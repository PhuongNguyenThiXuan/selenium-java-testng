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
        String thoughts = "This is a test\nThis is a test\nThis is a test";
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


    @Test
    public void TC_02() throws InterruptedException {
        String firstName = "Jessica";
        String lastName = "Nguyen";

        String number = "111-222-333-4444";
        String comments = "This is a test\nThis is a test";

        //1. Get url
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[contains(string(),'Login')]")).click();
        Thread.sleep(3000);

        //3. Open PIM
        driver.findElement(By.xpath("//span[contains(string(),'PIM')]//parent::a")).click();
        Thread.sleep(3000);

        //4. Open Add Employee
        driver.findElement(By.xpath("//a[contains(string(),'Add Employee')]//parent::li")).click();
        Thread.sleep(3000);

        //5. Input valid data for First Name/ Last Name
        String employeeID = driver.findElement(By.xpath("//label[contains(string(),'Employee Id')]//parent::div//following-sibling::div/input")).getText();
        String userName = "Jessica" + new Random().nextInt(9999);
        String password = "123456a@A";
        String confirmPassword = "123456a@A";

        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(lastName);
        driver.findElement(By.xpath("//p[contains(string(),'Create Login Details')]//following-sibling::div//span")).click();
        driver.findElement(By.xpath("//label[contains(string(),'Username')]//parent::div/following-sibling::div/input")).sendKeys(userName);
        System.out.println(userName);
        driver.findElement(By.xpath("//label[text()='Password']//parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[contains(string(),'Confirm Password')]//parent::div/following-sibling::div/input")).sendKeys(confirmPassword);

        //6. Click Save
        driver.findElement(By.xpath("//button[contains(string(),' Save ')]")).click();
        Thread.sleep(5000);

        //7. Verify inputted data
        driver.findElement(By.xpath("//a[contains(string(),'Personal Details')]")).click();
        Thread.sleep(10000);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"),lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[contains(string(),'Employee Id')]//parent::div//following-sibling::div/input")).getText(),employeeID);

        //8. Click Immigration
        driver.findElement(By.xpath("//a[contains(string(),'Immigration')]")).click();
        Thread.sleep(3000);

        //9. Click Add in "Assigned Immigration Records" section
        driver.findElement(By.xpath("//h6[contains(string(),'Assigned Immigration Records')]//following-sibling::button")).click();
        driver.findElement(By.xpath("//h6[contains(string(),'Assigned Immigration Records')]//following-sibling::button")).click();

        //10. Input data for Number/ Comments then click Save
        driver.findElement(By.xpath("//label[contains(string(),'Number')]//parent::div//following-sibling::div/input")).sendKeys(number);
        driver.findElement(By.xpath("//label[contains(string(),'Comments')]//parent::div//following-sibling::div/textarea")).sendKeys(comments);
        driver.findElement(By.xpath("//button[contains(string(),' Save ')]")).click();
        Thread.sleep(3000);

        //11. Click Pencil icon
        driver.findElement(By.xpath("//div[contains(text(),'" + number + "')]//parent::div//parent::div[@role='row']//following-sibling::div//button/i[contains(@class,'bi-pencil-fill')]")).click();
        Thread.sleep(10000);

        //12. Verify inputted data
        Assert.assertEquals(driver.findElement(By.xpath("//label[contains(string(),'Number')]//parent::div//following-sibling::div/input")).getAttribute("value"),number);
        Assert.assertEquals(driver.findElement(By.xpath("//label[contains(string(),'Comments')]//parent::div//following-sibling::div/textarea")).getAttribute("value"),comments);

        //13. Click on User then select Logout
        driver.findElement(By.xpath("//span[contains(@class,'oxd-userdropdown-tab')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(string(),'Logout')]")).click();
        Thread.sleep(3000);

        //14. Login with account that have just created
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys(userName);
        System.out.println(userName);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(string(),'Login')]")).click();

    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
