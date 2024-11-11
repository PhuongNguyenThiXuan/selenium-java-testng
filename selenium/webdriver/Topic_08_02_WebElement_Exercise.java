package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_08_02_WebElement_Exercise {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Element_isDisplayed(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //Step 2: Verify Email/Age under18/Edu is displayed
        if(driver.findElement(By.cssSelector("input#mail")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#mail")).sendKeys("Automation Testing");
            System.out.println("Email text box is displayed");
        }else{
            System.out.println("Email text box is not displayed");
        }

        if(driver.findElement(By.cssSelector("input#under_18")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#under_18")).click();
            System.out.println("Age under 13 radio is displayed");
        }else{
            System.out.println("Age under 13 radio is not displayed");
        }

        if(driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()) {
            driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation Testing");
            System.out.println("Education text box is displayed");
        }else{
            System.out.println("Education text box is not displayed");
        }

        //Step 3: Verify Name: User5 is not displayed
        if(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
            System.out.println("User5 text is displayed");
        }else{
            System.out.println("User5 text is not displayed");
        }
    }


    @Test
    public void TC_02_Element_isEnable(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //Step 2: Verify element enable:
        if(driver.findElement(By.cssSelector("input#mail")).isEnabled()) {
            System.out.println("Email text box is enable");
        }else{
            System.out.println("Email text box is disable");
        }

        if(driver.findElement(By.cssSelector("input#under_18")).isEnabled()) {
            System.out.println("Age under 18 radio is enable");
        }else{
            System.out.println("Age under 18 radio is disable");
        }

        if(driver.findElement(By.cssSelector("textarea#edu")).isEnabled()) {
            System.out.println("Education text box is enable");
        }else{
            System.out.println("Education text is disable");
        }

        if(driver.findElement(By.cssSelector("select#job1")).isEnabled()) {
            System.out.println("Job Role 1 is enable");
        }else{
            System.out.println("Job Role 1 text is disable");
        }

        if(driver.findElement(By.cssSelector("select#job2")).isEnabled()) {
            System.out.println("Job Role 2 is enable");
        }else{
            System.out.println("Job Role 2 text is disable");
        }

        if(driver.findElement(By.cssSelector("input#development")).isEnabled()) {
            System.out.println("Interests (Developer) checkbox is enable");
        }else{
            System.out.println("Interests (Developer) checkbox is disable");
        }

        if(driver.findElement(By.cssSelector("input#slider-1")).isEnabled()) {
            System.out.println("Slider 01 is enable");
        }else{
            System.out.println("Slider 01 is disable");
        }


        //Step 3: Verify elenment disable
        if(driver.findElement(By.cssSelector("input#disable_password")).isEnabled()) {
            System.out.println("Password is enable");
        }else{
            System.out.println("Password is disable");
        }

        if(driver.findElement(By.cssSelector("input#radio-disabled")).isEnabled()) {
            System.out.println("Age (Radio button is disabled) is enable");
        }else{
            System.out.println("Age (Radio button is disabled) is disable");
        }

        if(driver.findElement(By.cssSelector("textarea#bio")).isEnabled()) {
            System.out.println("Biography is enable");
        }else{
            System.out.println("Biography is disable");
        }

        if(driver.findElement(By.cssSelector("input#check-disbaled")).isEnabled()) {
            System.out.println("Interest (Checkbox is disabled) is enable");
        }else{
            System.out.println("Interest (Checkbox is disabled) is disable");
        }

        if(driver.findElement(By.cssSelector("input#slider-2")).isEnabled()) {
            System.out.println("Slider 02 is enable");
        }else{
            System.out.println("Slider 02 is disable");
        }

    }

    @Test
    public void TC_03_Element_isSelected(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //Step 2:
        driver.findElement(By.cssSelector("input#under_18")).click();
        driver.findElement(By.cssSelector("input#java")).click();
        //Step 3: Check element step 2
        if(driver.findElement(By.cssSelector("input#under_18")).isSelected()) {
            System.out.println("Age: Under 18 is selected");
        }else{
            System.out.println("Age: Under 18 is de-selected");
        }
        if(driver.findElement(By.cssSelector("input#java")).isSelected()) {
            System.out.println("Languages: Java is selected");
        }else{
            System.out.println("Languages: Java is de-selected");
        }
        //Step 4: Click to uncheck "Languages: Java" checkbox
        driver.findElement(By.cssSelector("input#java")).click();
        //Step 5: Check element step 4
        if(driver.findElement(By.cssSelector("input#java")).isSelected()) {
            System.out.println("Languages: Java is selected");
        }else{
            System.out.println("Languages: Java is de-selected");
        }
    }

    @Test
    public void TC_04_Mailchimp_Validation(){
        //Email Marketing
        driver.get("https://login.mailchimp.com/signup/");

        //Step 2: Input Email
        driver.findElement(By.cssSelector("input#email")).sendKeys("png@yopmail.com");
        driver.findElement(By.cssSelector("label[for='new_username']")).click();
        //Step 3: Input Password to validation
        //Empty
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.not-completed.\\38 -char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());

        //Input number
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("123456");
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.not-completed.\\38 -char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //Input all lowercase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("abcde");
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.not-completed.\\38 -char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //Input all uppercase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("ABCDE");
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.not-completed.\\38 -char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //Input special chars
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("!@#$%");
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.not-completed.\\38 -char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //Input over 8 digits
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("A123456");
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.not-completed.\\38 -char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //Input over 50 digits
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("123456a@A123456a@A123456a@A123456a@A123456a@A123456");
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.completed.\\38 -char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.plus-50.error.not-completed")).isDisplayed());

        //Input valid password
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("123456a@A");
        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.completed.\\38 -char")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.plus-50.error.completed")).isDisplayed());
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
