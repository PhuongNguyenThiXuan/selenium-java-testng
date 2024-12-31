package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_16_Alert {
    WebDriver driver;
    By jsAlert = By.cssSelector("button[onclick='jsAlert()']");
    By jsConfirm = By.cssSelector("button[onclick='jsConfirm()']");
    By jsPrompt = By.cssSelector("button[onclick='jsPrompt()']");

    By result = By.cssSelector("p#result");
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Accept_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        //1. Click to button Click for JS Alert
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(jsAlert));
        Thread.sleep(2000);
        driver.findElement(jsAlert).click();
        //2. Verify the message displayed: "I am a JS Alert"
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS Alert");
        //3. Accept this alert
        alert.accept();
        //4. Verify Result
        String results = driver.findElement(result).getText();
        Assert.assertEquals(results,"You clicked an alert successfully");
    }

    @Test
    public void TC_02_Cancel_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        //1. Click to button Click for JS Alert
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(jsAlert));
        Thread.sleep(2000);
        driver.findElement(jsConfirm).click();
        //2. Verify the message displayed: "I am a JS Confirm"
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS Confirm");
        //3. Accept this alert
        alert.dismiss();
        //4. Verify Result
        String results = driver.findElement(result).getText();
        Assert.assertEquals(results,"You clicked: Cancel");
    }

    @Test
    public void TC_03_Prompt_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        //1. Click to button Click for JS Alert
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(jsAlert));
        Thread.sleep(2000);
        driver.findElement(jsPrompt).click();
        //2. Verify the message displayed: "I am a JS Confirm"
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS prompt");
        //3. Input text into prompt
        String text = "This is a test";
        alert.sendKeys(text);
        alert.accept();
        //4. Verify Result
        String results = driver.findElement(result).getText();
        Assert.assertEquals(results,"You entered: " + text );

    }

    @Test
    public void TC_04_Authentication_ByPass() throws InterruptedException{
        String username = "admin";
        String password = "admin";

        driver.get("http://"+ username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
        //1. Handle authentication alert with username/password: admin/admin
        Thread.sleep(2000);

        //2. Verify successfully message is displayed
        String content = driver.findElement(By.cssSelector("div#content p")).getText();
        Assert.assertEquals(content,"Congratulations! You must have the proper credentials.");
    }

    @Test
    public void TC_05_Authentication_Link() throws InterruptedException{
        String username = "admin";
        String password = "admin";

        driver.get("https://the-internet.herokuapp.com/");
        Thread.sleep(2000);

        String href = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        System.out.println(href);
        //1. Handle authentication alert with username/password: admin/admin
        driver.get(passUserToAuthenLink(href,username,password));
        Thread.sleep(2000);

        //2. Verify successfully message is displayed
        String content = driver.findElement(By.cssSelector("div#content p")).getText();
        Assert.assertEquals(content,"Congratulations! You must have the proper credentials.");
    }

    public String passUserToAuthenLink (String authenLink, String username, String password){
        String[] text = authenLink.split("//");
        return text[0] + "//" + username + ":" + password + "@" + text[1];
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
