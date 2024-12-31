package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_Custom_Checkbox {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Ubuntu() throws InterruptedException {
        driver.get("https://login.ubuntu.com/");
        By radio = By.cssSelector("input#id_new_user");
        By checkbox = By.cssSelector("input#id_accept_tos");

        //1. Click on radio button: "I don’t have an Ubuntu One account"
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(radio));
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(radio).isSelected());

        //2. Click on check box: "I have read and accept the Ubuntu One terms of service, data privacy policy and Canonical's SSO privacy notice."
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(checkbox));
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(checkbox).isSelected());
    }


    @Test
    public void TC_02_Google_Form() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        Thread.sleep(2000);
        By canthoRadio = By.cssSelector("div[data-value='Cần Thơ']");

        new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions
                .visibilityOfAllElements(driver.findElement(canthoRadio)));
        //1. Verify "Can Tho" is not selected yet
        Assert.assertTrue(driver.findElement(By.cssSelector("div[data-value='Cần Thơ'][aria-checked='false']")).isDisplayed());

        //2. Select "Can Tho" then Verify "Can Tho" is selected
        if(!driver.findElement(canthoRadio).isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(canthoRadio));
            Thread.sleep(3000);
            Assert.assertTrue(driver.findElement(By.cssSelector("div[data-value='Cần Thơ'][aria-checked='true']")).isDisplayed());
            Assert.assertTrue(driver.findElement(canthoRadio).getDomAttribute("aria-checked").equals("true"));
        }
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
