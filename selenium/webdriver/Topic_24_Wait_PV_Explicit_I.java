
package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_24_Wait_PV_Explicit_I {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeMethod
    public void beforeClass(){
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01() {
        //Chờ cho 1 alert được xuất hiện trong html + sau đó switch vào
        explicitWait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        //Element clickable (Button/ Check box/ Radio/ Link/ Image...)
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));
        driver.findElement(By.cssSelector("")).click();

        //Element visible (All elements)
        //Get text/ get css/ attribute/ displayed...
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isDisplayed());

        //Element selected (Check box/ Radio..)
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isSelected());

        //Invisible (All elements)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));
        Assert.assertFalse(driver.findElement(By.cssSelector("")).isSelected());

        //Presence (All elements in html)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

        //Element size
        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""),9));
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getSize(),9);

        //Attribute
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""),"value","Hello"));
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getAttribute("value"),"Hello");

        //Text
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""),"Hello"));
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getText(),"Hello");

    }

    @AfterMethod
    public void afterClass(){
        driver.quit();
    }
}
