package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.print.DocFlavor;
import java.time.Duration;
import java.util.Set;

public class Topic_21_Window {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Git() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        String parentWindow = driver.getWindowHandle();
        System.out.println(parentWindow);
        //1. Click Google link
        driver.findElement(By.xpath("//a[contains(text(),'GOOGLE')]")).click();
        //2. Verify title of new window
        switchToWindowByTitle("Google");
        Thread.sleep(2000);

        //3. Switch to parent window
        switchToWindowByID(parentWindow);
        //4. Click Facebook link
        driver.findElement(By.xpath("//a[contains(text(),'FACEBOOK')]")).click();
        //5. Verify title of new window
        switchToWindowByTitle("Facebook - log in or sign up");
        Thread.sleep(2000);

        //6. Switch to parent window
        switchToWindowByID(parentWindow);
        //7. Click Tiki link
        driver.findElement(By.xpath("//a[contains(text(),'TIKI')]")).click();
        //8. Verify title of new window
        switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        Thread.sleep(2000);

        //9. Close all window except Parent Window
        closeAllWindowsWithoutParent(parentWindow);
    }


    @Test
    public void TC_02_LiveTechPanda() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        String parentWindow = driver.getWindowHandle();
        System.out.println(parentWindow);
        Thread.sleep(3000);
        //1. Click on Mobile tab
        driver.findElement(By.xpath("//a[contains(text(),'Mobile')]")).click();
        //2. Click on Add to Compare button of Sony Xperia
        driver.findElement(
                By.xpath("//a[contains(text(),'Sony Xperia')]//parent::h2//following-sibling::div//a[contains(text(),'Add to Compare')]")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),"The product Sony Xperia has been added to comparison list.");
        //3. Click on Add to Compare button of Samsung Galaxy
        driver.findElement(
                By.xpath("//a[contains(text(),'Samsung Galaxy')]//parent::h2//following-sibling::div//a[contains(text(),'Add to Compare')]")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),"The product Samsung Galaxy has been added to comparison list.");
        //4. Click on Compare button
        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        //5. Switch to compare window
        switchToWindowByTitle("Products Comparison List - Magento Commerce");
        //6. Close tab then switch to Parent Window
        closeAllWindowsWithoutParent(parentWindow);
        //7. Click on Clear All button => accept the Alert
        driver.findElement(By.xpath("//a[contains(text(),'Clear All')]")).click();
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        alert.accept();
        Thread.sleep(2000);
        //8. Verify the message displayed
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),"The comparison list was cleared.");

    }

    @Test
    public void TC_03_Cambridge() throws InterruptedException {
        driver.get("https://dictionary.cambridge.org/vi/");
        String parentWindow = driver.getWindowHandle();
        System.out.println(parentWindow);
        Thread.sleep(3000);
        //1. Click on Login button
        driver.findElement(By.xpath("//div[contains(@class,'hdib-s')]//span[contains(text(),'Đăng nhập')]")).click();
        //2. Switch to new window
        switchToWindowByTitle("Login");
        //3. Click on Login button
        driver.findElement(By.cssSelector("input[value='Log in']")).click();
        Thread.sleep(3000);
        //4. Verify the error message should be displayed under 2 fields: Email/Password
        WebElement msgEmail = driver.findElement(By.xpath("//input[@name='username']//following-sibling::span[@role='alert']"));
        WebElement msgPassword = driver.findElement(By.xpath("//input[@name='password']//following-sibling::span[@role='alert']"));

        Assert.assertTrue(msgEmail.isDisplayed());
        Assert.assertEquals(msgEmail.getText(),"This field is required");
        Assert.assertTrue(msgPassword.isDisplayed());
        Assert.assertEquals(msgPassword.getText(),"This field is required");
        //5. Close Login window then back to Parent Window
        closeAllWindowsWithoutParent(parentWindow);
        //6. Input value into Search box then click Search
        String searchKey = "automation";
        driver.findElement(By.cssSelector("input#searchword")).sendKeys(searchKey);
        driver.findElement(By.cssSelector("button[aria-label='Search']")).click();
        //7. Verify the result of Search page
        Assert.assertEquals(driver.findElement(
                By.xpath("//div[@id='cald4-1']/following-sibling::div[contains(@class,'pos-header')]//span[contains(@class,'headword')]"))
                .getText(),searchKey);
    }

    @Test
    public void TC_04_Harvard() throws InterruptedException {
        driver.get("https://courses.dce.harvard.edu/");
        String parentWindow = driver.getWindowHandle();
        System.out.println(parentWindow);
        Thread.sleep(3000);
        //1. Click on Student login button
        driver.findElement(By.cssSelector("a[data-action='login']")).click();
        //2. Switch to new window
        switchToWindowByTitle("Harvard Division of Continuing Education Login Portal");
        //3. Verify DCE Login Portal is displayed
        Assert.assertEquals(driver.findElement(By.cssSelector("h1.c34c4ffae")).getText(),"DCE Login Portal");
        //4. Close Login window then back to previous page
        closeAllWindowsWithoutParent(parentWindow);
        //5. Verify the message: "Authentication was not successful. Please try again." should be displayed
        Assert.assertEquals(driver.findElement(By.cssSelector("p.sam-wait__message")).getText(),"Authentication was not successful. Please try again.");
        //6. Close Authentication popup then input data for Search Courses
        driver.findElement(By.cssSelector("button.sam-wait__close")).click();
        driver.findElement(By.cssSelector("input#crit-keyword")).sendKeys("Data Science: An Artificial Ecosystem");
        new Select(driver.findElement(By.cssSelector("select#crit-srcdb")))
                .selectByVisibleText("Harvard Summer School 2025");
        new Select(driver.findElement(By.cssSelector("select#crit-summer_school")))
                .selectByVisibleText("Harvard College");
        new Select(driver.findElement(By.cssSelector("select#crit-session")))
                .selectByVisibleText("Any Part of Term");
        driver.findElement(By.cssSelector("button#search-button")).click();
        //7. Verify Search result
        Assert.assertEquals(driver.findElement(By.cssSelector("span.result__title")).getText(),"Data Science: An Artificial Ecosystem");
    }

    public void switchToWindowByTitle(String title){
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows){
            driver.switchTo().window(runWindow);
            String currentWin = driver.getTitle();
            if(currentWin.equals(title)){
                break;
            }
        }

    }

    public void switchToWindowByID(String parentID){
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows){
            if(runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public boolean closeAllWindowsWithoutParent (String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
        if (driver.getWindowHandles().size()==1)
            return true;
        else
            return false;
    }


    @AfterClass
    public void afterClass(){
        //driver.quit();
    }
}
