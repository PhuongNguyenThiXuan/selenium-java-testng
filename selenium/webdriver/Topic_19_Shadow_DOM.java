package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_19_Shadow_DOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Shadow_DOM(){
        driver.get("https://automationfc.github.io/shadow-dom/");
        //1. Verify all text are displayed
        String text4 = driver.findElement(By.cssSelector("a[href]")).getText();
        System.out.println("Text4: " + text4);

        //Find the element contains 1st ShadowHost
        WebElement firstShadowHostElement = driver.findElement(By.xpath("//div[@id='shadow_host']"));
        //Get ShadowRoot
        SearchContext firstShadowRoot = firstShadowHostElement.getShadowRoot();

        String text3 = firstShadowRoot.findElement(By.cssSelector("a")).getText();
        System.out.println("Text3: " + text3);
        firstShadowRoot.findElement(By.cssSelector("input[type='text']")).sendKeys("Selenium");
        firstShadowRoot.findElement(By.cssSelector("input[type='checkbox']")).click();
        Assert.assertTrue(firstShadowRoot.findElement(By.cssSelector("input[type='checkbox']")).isSelected());
        firstShadowRoot.findElement(By.cssSelector("input[type='checkbox']")).click();
        Assert.assertFalse(firstShadowRoot.findElement(By.cssSelector("input[type='checkbox']")).isSelected());

        //Find the element contains 2nd ShadowHost
        WebElement secondShadowElement = firstShadowRoot.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext secondShadowRoot = secondShadowElement.getShadowRoot();

        String text2 = secondShadowRoot.findElement(By.cssSelector("div#nested_shadow_content div")).getText();
        System.out.println("Text2: " + text2);

        String text1 = firstShadowRoot.findElement(By.cssSelector("span#shadow_content span")).getText();
        System.out.println("Text1: " + text1);
    }


    @Test
    public void TC_02_Shadow_DOM_POPUP() throws InterruptedException {
        driver.get("https://books-pwakit.appspot.com/");
        Thread.sleep(2000);

        WebElement firstShadowHostElement = driver.findElement(By.cssSelector("book-app[apptitle='BOOKS']"));
        SearchContext firstShadowRoot = firstShadowHostElement.getShadowRoot();
        //2. Input into Search textbox: "Harry Potter"
        firstShadowRoot.findElement(By.cssSelector("input#input")).sendKeys("Harry Potter");
        Thread.sleep(2000);

        WebElement secondShadowHostElement = firstShadowRoot.findElement(By.cssSelector("app-toolbar>book-input-decorator"));
        SearchContext secondShadowRoot = secondShadowHostElement.getShadowRoot();
        //3. Click search icon
        secondShadowRoot.findElement(By.cssSelector("div.icon")).click();
        Thread.sleep(2000);
        //4. Print all product's title
        WebElement thirdShadowHostElement = firstShadowRoot.findElement(By.cssSelector("main.main-content>book-explore"));
        SearchContext thirdShadowRoot = thirdShadowHostElement.getShadowRoot();

        List<WebElement> forthShadowHostElement = thirdShadowRoot.findElements(By.cssSelector("ul.books>li>book-item"));
        for (WebElement element : forthShadowHostElement){
            SearchContext shadowRoot = element.getShadowRoot();
            System.out.println(shadowRoot.findElement(By.cssSelector("div.title-container>h2")).getText());
        }
    }


    @AfterClass
    public void afterClass(){
        //driver.quit();
    }
}
