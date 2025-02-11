package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Topic_18_Random_Popup {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        action = new Actions(driver);
    }

    @Test
    public void TC_01_JavacodeGeeks_Fixed_Not_In_DOM() throws InterruptedException {
        driver.get("https://www.javacodegeeks.com/");
        Thread.sleep(15000);

        By popupSelleBook = By.xpath("//div[starts-with(@data-title,'Newsletter') and not(contains(@style,'display:none'))]");
        By popup = By.cssSelector("div.lepopup-element-rectangle.lepopup-fadeIn");

        if(driver.findElements(popupSelleBook).size()>0 && driver.findElements(popupSelleBook).get(0).isDisplayed()){
            driver.findElement(By.cssSelector("a[onclick='return lepopup_close();']")).click();
            Thread.sleep(2000);
        }

//        if(driver.findElement(popup).isDisplayed()){
//            driver.findElement(By.cssSelector("div.lepopup-element-html.lepopup-fadeIn a")).click();
//            driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
//            Thread.sleep(2000);
//            driver.findElement(By.cssSelector("button#search-submit")).click();
//            Thread.sleep(2000);
//
//            //2. Verify the number selected is correctly
//            List<WebElement> searchItem = driver.findElements(By.cssSelector("li.post-item div.post-details h2"));
//            for (WebElement item:searchItem){
//                if(item.getText().trim().equals("Agile Testing Explained")) {
//                    Assert.assertTrue(item.isDisplayed());
//                }
//            }
//        }
            driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("button#search-submit")).click();
            Thread.sleep(2000);
            //2. Verify the number selected is correctly
            List<WebElement> searchItem = driver.findElements(By.cssSelector("li.post-item div.post-details h2"));
            for (WebElement item:searchItem){
                if(item.getText().trim().equals("Agile Testing Explained")) {
                    Assert.assertTrue(item.isDisplayed());
                }
            }

    }


    @Test
    public void TC_02_KMPlayer_In_DOM() throws InterruptedException {
        driver.get("https://www.kmplayer.com/");
        Thread.sleep(5000);

        By popup = By.cssSelector("div.pop-container");

        if (driver.findElement(popup).isDisplayed()){
            driver.findElement(By.cssSelector("div.close")).click();
            action.moveToElement(driver.findElement(By.cssSelector("li.mobile.kmp")))
                    .pause(Duration.ofSeconds(1))
                    .perform();
            action.moveToElement(driver.findElement(By.cssSelector("ul.mobile.kmp li.kmp")))
                    .pause(Duration.ofSeconds(1))
                    .click()
                    .perform();
            Assert.assertTrue(driver.findElement(By.cssSelector("div.sub h1")).getText().equals("KMP - Video Player for Android"));
        } else {
            action.moveToElement(driver.findElement(By.cssSelector("li.mobile.kmp")))
                    .pause(Duration.ofSeconds(1))
                    .perform();
            action.moveToElement(driver.findElement(By.cssSelector("ul.mobile.kmp li.kmp")))
                    .pause(Duration.ofSeconds(1))
                    .click()
                    .perform();
            Assert.assertTrue(driver.findElement(By.cssSelector("div.sub h1")).getText().equals("KMP - Video Player for Android"));

        }
    }

    @Test
    public void TC_03_DeHieu_In_DOM() throws InterruptedException {
        driver.get("https://dehieu.vn/");
        Thread.sleep(5000);

        By popup = By.cssSelector("div.modal-content");

        if (driver.findElement(popup).isDisplayed()){
            driver.findElement(By.cssSelector("button.close")).click();
            driver.findElement(By.cssSelector("ul.menu-content-header a.signin-site-menu")).click();
            Assert.assertTrue(driver.findElement(By.cssSelector("div.b-login")).isDisplayed());
        }else {
            driver.findElement(By.cssSelector("ul.menu-content-header a.signin-site-menu")).click();
            Assert.assertTrue(driver.findElement(By.cssSelector("div.b-login")).isDisplayed());
        }
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
