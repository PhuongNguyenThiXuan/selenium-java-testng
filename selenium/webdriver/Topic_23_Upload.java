package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_23_Upload {
    WebDriver driver;
    String uploadFilePath = System.getProperty("user.dir") + "/uploadFile/";
    String image1 = "c5ee60e6f1766167e34d7f06e3ae20dc.gif";
    String image2 = "779370797028bf3e580fc92f1eb385be.jpg";
    String image3 = "good-morning-gif-28.gif";

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Upload_Single() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By inputFile = By.xpath("//input[@type='file']");

        driver.findElement(inputFile).sendKeys(uploadFilePath+image1);
        driver.findElement(inputFile).sendKeys(uploadFilePath+image2);
        driver.findElement(inputFile).sendKeys(uploadFilePath+image3);
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image3 + "']")).isDisplayed());

        List<WebElement> startButton = driver.findElements(By.cssSelector("tbody button.start"));
        for (WebElement start : startButton){
            start.click();
            Thread.sleep(3000);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + image1 +
                "']/parent::p/parent::td//following-sibling::td//button[@class='btn btn-danger delete']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + image2 +
                "']/parent::p/parent::td//following-sibling::td//button[@class='btn btn-danger delete']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + image3 +
                "']/parent::p/parent::td//following-sibling::td//button[@class='btn btn-danger delete']")).isDisplayed());
    }

    @Test
    public void TC_02_Upload_Multiple() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By inputFile = By.xpath("//input[@type='file']");

        driver.findElement(inputFile).sendKeys(
                uploadFilePath+image1 + "\n" + uploadFilePath+image2 + "\n" + uploadFilePath+image3);
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image3 + "']")).isDisplayed());

        List<WebElement> startButton = driver.findElements(By.cssSelector("tbody button.start"));
        for (WebElement start : startButton){
            start.click();
            Thread.sleep(3000);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + image1 +
                "']/parent::p/parent::td//following-sibling::td//button[@class='btn btn-danger delete']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + image2 +
                "']/parent::p/parent::td//following-sibling::td//button[@class='btn btn-danger delete']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + image3 +
                "']/parent::p/parent::td//following-sibling::td//button[@class='btn btn-danger delete']")).isDisplayed());
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
