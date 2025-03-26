
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

public class Topic_24_Wait_PV_Explicit_II {
    WebDriver driver;
    WebDriverWait explicitWait;

    String uploadFilePath = System.getProperty("user.dir") + "/uploadFile/";
    String image1 = "2Mb3.jpg";
    String image2 = "3Mb2.jpg";
    String image3 = "12Mb4.jpg";

    @BeforeMethod
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
    }

    @Test
    public void TC_01() {
        //1. Access url
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        //2. Wait "Date Time" is visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.calendarContainer")));

        //3. Print out the contains of Selected Dates: "No Selected Dates to display."
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div.datesContainer span"),"No Selected Dates to display."));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.datesContainer span")).getText(),"No Selected Dates to display.");

        //4. Select any date time
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='calendarContainer']//a[contains(text(),'26')]")));
        driver.findElement(By.xpath("//div[@class='calendarContainer']//a[contains(text(),'26')]")).click();

        //5. Wait to "Ajax loading icon" is invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));

        //6. Wait to Selected Dates = 26 is selected
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("td.rcSelected"),"class","rcSelected"));
        Assert.assertTrue(driver.findElement(By.cssSelector("td.rcSelected")).getAttribute("class").contains("rcSelected"));

        //7. Print out the contains of Selected Dates: "Wednesday, March 26, 2025"
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div.datesContainer span"),"Wednesday, March 26, 2025"));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.datesContainer span")).getText(),"Wednesday, March 26, 2025");

    }

    @Test
    public void TC_02() {
        //1. Access url
        driver.get("https://gofile.io/home");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        //2. Upload file then verify this file have been uploaded
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("main#index_main div.animate-spin")));

        By inputFile = By.xpath("//input[@type='file']");
        driver.findElement(inputFile).sendKeys(
                uploadFilePath+image1 + "\n" + uploadFilePath+image2 + "\n" + uploadFilePath+image3);

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.progress-container")));
        //3. Verify "Upload Completed" should be displayed
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div.text-center h2"),"Upload Complete"));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.text-center h2")).getText(),"Upload Complete");

        //4. Get link
        String link = driver.findElement(By.cssSelector("a.linkSuccessCard")).getDomAttribute("href");
        driver.get(link);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("main#index_main div.animate-spin")));

        //5. Verify that icon download and play is displayed on each uploaded file
        String downloadButtonImage1 = "//a[text()='"+ image1 +"']/parent::div/parent::div/following-sibling::div//button[contains(@class,'item_download')]";
        String downloadButtonImage2 = "//a[text()='"+ image2 +"']/parent::div/parent::div/following-sibling::div//button[contains(@class,'item_download')]";
        String downloadButtonImage3 = "//a[text()='"+ image3 +"']/parent::div/parent::div/following-sibling::div//button[contains(@class,'item_download')]";

        String playButtonImage1 = "//a[text()='"+ image1 +"']/parent::div/parent::div/following-sibling::div//button[contains(@class,'item_play')]";
        String playButtonImage2 = "//a[text()='"+ image2 +"']/parent::div/parent::div/following-sibling::div//button[contains(@class,'item_play')]";
        String playButtonImage3 = "//a[text()='"+ image3 +"']/parent::div/parent::div/following-sibling::div//button[contains(@class,'item_play')]";

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(downloadButtonImage1)));
        Assert.assertTrue(driver.findElement(By.xpath(downloadButtonImage1)).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath(downloadButtonImage2)).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath(downloadButtonImage3)).isDisplayed());


        Assert.assertTrue(driver.findElement(By.xpath(playButtonImage1)).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath(playButtonImage2)).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath(playButtonImage3)).isDisplayed());

    }


    @AfterMethod
    public void afterClass(){
        driver.quit();
    }
}
