package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_24_Wait_PVII_Fluent {
    WebDriver driver;
    WebDriverWait explicit;
    FluentWait fluentWait;
    FluentWait<WebDriver> fluentWaitDriver;
    FluentWait<WebElement> fluentWaitElement;
    FluentWait<Boolean> fluentWaitBoolean;

    String uploadFilePath = System.getProperty("user.dir") + "/uploadFile/";
    String image1 = "2Mb3.jpg";
    String image2 = "3Mb2.jpg";
    String image3 = "12Mb4.jpg";


    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        //Khởi tạo
//        fluentWaitDriver = new FluentWait<>(driver);
//        fluentWaitElement = new FluentWait<>(driver.findElement(By.cssSelector("")));
//        fluentWaitBoolean = new FluentWait<>(true);
//        driver.manage().window().maximize();
    }

    @Test
    public void TC_01(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start button")).click();

        //0,5s kiem tra 1 lan, kiem tra trong 5s
        fluentWaitDriver = new FluentWait<>(driver);
        fluentWaitDriver.withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        //Cach 1
        String helloText = (String) fluentWaitDriver.until(new Function<WebDriver, String>() {
                    @Override
                    public String apply(WebDriver webDriver) {
                        System.out.println("------Dang tim element va getText-----"); //Mong doi: tim 10 lan
                        return webDriver.findElement(By.cssSelector("div#finish>h4")).getText();
                    }
                });
        Assert.assertEquals(helloText,"Hello World!");

        //Cach 2
        boolean helloStatus = fluentWaitDriver.until(new Function<WebDriver, Boolean>(){
            @Override
            public Boolean apply(WebDriver webDriver) {
                return driver.findElement(By.cssSelector("div#finish>h4")).getText().equals("Hello World!");
            }
        });
        Assert.assertTrue(helloStatus);
    }

    @Test
    public void TC_02() {
        driver.get("https://automationfc.github.io/fluent-wait/");

        WebElement countDown = driver.findElement(By.cssSelector("div#javascript_countdown_time"));

        //Đếm ngược giây về 01:01:00
        fluentWaitElement = new FluentWait<>(countDown);
        fluentWaitElement.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .until(new Function<WebElement, Boolean>(){
                    @Override
                    public Boolean apply(WebElement webElement) {
                        System.out.println(countDown.getText());
                        return countDown.getText().endsWith("01:01:00");
                    }

        });
    }


    @Test
    public void TC_03() {
        //1. Access url
        driver.get("https://gofile.io/home");
        //2. Upload files
        fluentWaitDriver = new FluentWait<>(driver);
        fluentWaitDriver.withTimeout(Duration.ofSeconds(50))
                .pollingEvery(Duration.ofMillis(333))
                .ignoring(NoSuchElementException.class);

        By inputFile = By.xpath("//input[@type='file']");
        driver.findElement(inputFile).sendKeys(
                uploadFilePath+image1 + "\n" + uploadFilePath+image2 + "\n" + uploadFilePath+image3);

        //3. Wait until upload completed
        String completedText = (String) fluentWaitDriver.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver webDriver) {
                System.out.println("------Dang upload images-----");
                return webDriver.findElement(By.cssSelector("div.text-center h2")).getText();
            }
        });
        Assert.assertEquals(completedText,"Upload Complete");
    }

    //Tim element voi polling time 1s/lan
    public WebElement findElement (By by){
        //Khai bao + khoi tao
        FluentWait fluentWait  = new FluentWait(driver);

        //Config Time/ Polling/ Exception
        //1. Viết chia ra theo từng function => dễ debug xem đang fail ở đâu
//        fluentWait.withTimeout(Duration.ofSeconds(15));
//        fluentWait.pollingEvery(Duration.ofSeconds(1));
//        fluentWait.ignoring(NoSuchElementException.class);

        //2. Viết chung như Action => gọn nhưng khó debug
        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        //Condition
        return (WebElement) fluentWait.until(new Function<WebDriver,WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                System.out.println("------Dang tim element va getText-----");
                return driver.findElement(by);
            }
        });
    }


    //Kiểm tra element hiển thị: isDisplayed()
    public boolean isElementDisplayed (By by){
        //Khai bao + khoi tao
        FluentWait fluentWait  = new FluentWait(driver);

        //Config Time/ Polling/ Exception
        fluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        //Condition
        return (boolean) fluentWait.until(new Function<WebDriver,Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElement(by).isDisplayed();
            }
        });
    }





    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
