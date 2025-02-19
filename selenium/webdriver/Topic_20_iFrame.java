package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.print.DocFlavor;
import java.time.Duration;
import java.util.List;

public class Topic_20_iFrame {
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
    public void TC_01_iFrame_FB() throws InterruptedException {
        driver.get("https://toidicodedao.com/");
        Thread.sleep(2000);
        WebElement iframe = driver.findElement(By.cssSelector("div.fb_iframe_widget span iframe"));

        driver.switchTo().frame(iframe);
        System.out.println(driver.findElement(By.cssSelector("div._1drq")).getText());
    }


    @Test
    public void TC_02_iFrame_Survey() throws InterruptedException {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        Thread.sleep(2000);

        WebElement iframe = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"));

        driver.switchTo().frame(iframe);
        //1. Select Year/Residence/Gender
        new Select(driver.findElement(By.xpath("//label[contains(text(),'Year')]//following-sibling::select")))
                .selectByVisibleText("Freshman");
        new Select(driver.findElement(By.xpath("//label[contains(text(),'Residence')]//following-sibling::select")))
                .selectByVisibleText("West Dorm");
        driver.findElement(By.xpath("//label[contains(text(),'Male')]")).click();

        driver.findElement(By.cssSelector("input#FSsubmit")).click();
        //2. Click on Login button
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("div.osano-cm-dialog button.osano-cm-close")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("nav.header--desktop-floater div.container a.menu-item-login")).click();
        //3. Do not input any data, click on Login button at Login form
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("button#login")).click();
        //4. Verify error message should be displayed
        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(),
                "Username and password are both required.");

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
