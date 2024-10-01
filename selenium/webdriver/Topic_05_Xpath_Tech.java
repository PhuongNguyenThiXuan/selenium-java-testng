package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_05_Xpath_Tech {
    WebDriver driver;


    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01(){
        driver.get("https://automationfc.github.io/basic-form/");

        //nested
        Assert.assertEquals(driver.findElement(By.xpath("//h5[@id='nested']")).getText(),"Hello World! (Ignore Me) @04:45 PM");
        //text()=
        Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Selenium WebDriver API']")).getText(),"Selenium WebDriver API");
        //contains(text(),'')
        Assert.assertEquals(driver.findElement(By.xpath("//h5[contains(text(),'Hacker')]")).getText(),"I'm a Hacker - 18 years old - living in Viet Nam - 15/03/2020");
        //contains(.,'')
        System.out.println(driver.findElement(By.xpath("//h5[contains(.,'Michael Jackson')]")).getSize());
        //contains(string(),'')
        System.out.println(driver.findElement(By.xpath("//h5[contains(string(),'Michael Jackson')]")).getSize());
        //concat
        Assert.assertEquals(driver.findElement(By.xpath("//span[text()=concat('Hello \"John\", What',\"'s happened?\")]")).getText(),"Hello \"John\", What's happened?");

    }


    @Test
    public void TC_02(){
       driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        //and
        driver.findElement(By.xpath("//input[@type='email' and @id='email']"));
        //or
        System.out.println(driver.findElement(By.xpath("//input[@name='login[username]' or @name='email']")).getSize());

    }

    @Test
    public void TC_03(){
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        //not
        driver.findElement(By.xpath("//a[contains(text(),'15')]")).click();
        driver.findElement(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']"));
    }

    @Test
    public void TC_04(){
        driver.get("https://automationfc.github.io/jquery-selectable/");

        //position()
        Assert.assertEquals(driver.findElement(By.xpath("//ol//li[5]")).getText(),"5");
        Assert.assertEquals(driver.findElement(By.xpath("//ol[@id='selectable']/li[position()='9']")).getText(),"9");
        //last()
        Assert.assertEquals(driver.findElement(By.xpath("//ol//li[count(//li)]")).getText(),"20");
        Assert.assertEquals(driver.findElement(By.xpath("//ol//li[last()]")).getText(),"20");
        //last()-1
        Assert.assertEquals(driver.findElement(By.xpath("//ol//li[last()-1]")).getText(),"19");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
