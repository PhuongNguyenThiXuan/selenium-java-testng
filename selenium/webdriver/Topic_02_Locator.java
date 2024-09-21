package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_02_Locator {
    WebDriver driver;


    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_find_by_id(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //Search
        driver.findElements(By.id("txtSearch"));
        //Register
        //Login
        //Register new account
        driver.findElements(By.id("txtFirstname"));
        driver.findElements(By.id("txtEmail"));
        driver.findElements(By.id("txtCEmail"));
        driver.findElements(By.id("txtPassword"));
        driver.findElements(By.id("txtCPassword"));
        driver.findElements(By.id("txtPhone"));
        //I agree
        driver.findElements(By.id("chkRight"));
        //Registration
        //Register Now
        driver.findElements(By.id("btndknfooter"));
    }


    @Test
    public void TC_02_find_by_name(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //Search
        driver.findElements(By.name("txtSearch"));
        //Register
        //Login
        //Register new account
        driver.findElements(By.name("txtFirstname"));
        driver.findElements(By.name("txtEmail"));
        driver.findElements(By.name("txtCEmail"));
        driver.findElements(By.name("txtPassword"));
        driver.findElements(By.name("txtCPassword"));
        driver.findElements(By.name("txtPhone"));
        //I agree
        driver.findElements(By.name("chkRight"));
        //Registration
        //Register Now
        driver.findElements(By.name("btndknfooter"));
    }

    @Test
    public void TC_03_find_by_className(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //Search
        driver.findElements(By.className("inputsearch2"));
        //Register
        driver.findElements(By.className("box-item-login"));
        //Login
        driver.findElements(By.className("bor"));
        //Register new account
        //I agree
        driver.findElements(By.className("marleft0"));
        //Registration
        driver.findElements(By.className("fs16"));
        //Register Now
        driver.findElements(By.className("martop15"));
    }

    @Test
    public void TC_04_find_by_tagName(){

    }

    @Test
    public void TC_05_find_by_linkText(){
        //Register
        driver.findElements(By.linkText("Đăng Ký"));
        //Login]n
        driver.findElements(By.linkText("Đăng Nhập"));
    }

    @Test
    public void TC_06_find_by_partialLinkText(){
//Register
        driver.findElements(By.partialLinkText("Ký"));
        //Login]n
        driver.findElements(By.partialLinkText("Nhập"));
    }


    @Test
    public void TC_07_find_by_cssSelector(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //Search
        driver.findElements(By.cssSelector("input[placeholder=\"Tìm khóa học\"]"));
        //Register
        driver.findElements(By.cssSelector("span[class=\"box-item-login\"]"));
        //Login
        driver.findElements(By.cssSelector("span[class=\"box-item-login bor\"]"));
        //Register new account
        driver.findElements(By.cssSelector("input[id=\"txtFirstname\"]"));
        driver.findElements(By.cssSelector("input[id=\"txtEmail\"]"));
        driver.findElements(By.cssSelector("input[id=\"txtCEmail\"]"));
        driver.findElements(By.cssSelector("input[id=\"txtPassword\"]"));
        driver.findElements(By.cssSelector("input[name=\"txtCPassword\"]"));
        driver.findElements(By.cssSelector("input[type=\"number\"]"));
        //I agree
        driver.findElements(By.cssSelector("input[class=\"marleft0\"]"));
        //Registration
        driver.findElements(By.cssSelector("button[class=\"btn_pink_sm fs16\"]"));
        //Register Now
        driver.findElements(By.cssSelector("button[onclick=\"showDangky();\"]"));
    }


    @Test
    public void TC_08_find_by_xpath(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //Search
        driver.findElements(By.xpath("//input[@placeholder=\"Tìm khóa học\"]"));
        //Register
        driver.findElements(By.xpath("//span[@class=\"box-item-login\"]"));
        //Login
        driver.findElements(By.xpath("//span[@class=\"box-item-login bor\"]"));
        //Register new account
        driver.findElements(By.xpath("//input[@id=\"txtFirstname\"]"));
        driver.findElements(By.xpath("//input[@id=\"txtEmail\"]"));
        driver.findElements(By.xpath("//input[@id=\"txtCEmail\"]"));
        driver.findElements(By.xpath("//input[@id=\"txtPassword\"]"));
        driver.findElements(By.xpath("//input[@name=\"txtCPassword\"]"));
        driver.findElements(By.xpath("//input[@type=\"number\"]"));
        //I agree
        driver.findElements(By.xpath("//input[@class=\"marleft0\"]"));

        int linkSize = driver.findElements(By.xpath("//a[@href=\"https://alada.vn/tai-khoan/dang-ky.html\"]")).size();
        System.out.println("linkSize: " + linkSize);

        //Registration
        driver.findElements(By.xpath("//button[@class=\"btn_pink_sm fs16\"]"));
        //Register Now
        driver.findElements(By.xpath("//button[@onclick=\"showDangky();\"]"));

    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
