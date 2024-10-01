package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_04_Register {
    WebDriver driver;


    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Register_With_Empty_Data(){
        //1. Launch website
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //2. Click on "DANG KY" button
        driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();

        //3. Verify error message
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText(),"Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(),"Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(),"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(),"Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),"Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(),"Vui lòng nhập số điện thoại.");
    }

    @Test
    public void TC_02_Register_With_Invalid_Email(){
        //1. Launch website
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //2. Input data for all fields (exception Email and Confirm Email)
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Automation FC"); //1
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("123@345@789"); //2
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("123@345@789"); //3
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456a@A"); //4
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456a@A"); //5
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0909090909"); //6

        //3. Click on "DANG KY" button
        driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();

        //4. Verify error message
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(),"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(),"Vui lòng nhập lại email hợp lệ");
    }

    @Test
    public void TC_03_Register_With_Incorrect_Confirm_Email(){
        //1. Launch website
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //2. Input data for all fields (exception Email and Confirm Email)
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Automation FC"); //1
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("test@yopmail.com"); //2
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("test@yopmail.c"); //3
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456a@A"); //4
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456a@A"); //5
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0909090909"); //6

        //3. Click on "DANG KY" button
        driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();

        //4. Verify error message
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(),"Email nhập lại không đúng"); //3
    }

    @Test
    public void TC_04_Register_With_Password_Lower_Six_Characters(){
        //1. Launch website
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //2. Input data for all fields (exception Email and Confirm Email)
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Automation FC"); //1
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("test@yopmail.com"); //2
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("test@yopmail.com"); //3
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123"); //4
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123"); //5
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0909090909"); //6

        //3. Click on "DANG KY" button
        driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();

        //4. Verify error message
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(),"Mật khẩu phải có ít nhất 6 ký tự"); //4
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),"Mật khẩu phải có ít nhất 6 ký tự"); //5
    }

    @Test
    public void TC_05_Register_With_Incorrect_Confirm_Password(){
        //1. Launch website
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //2. Input data for all fields (exception Email and Confirm Email)
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Automation FC"); //1
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("test@yopmail.com"); //2
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("test@yopmail.com"); //3
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456a@A"); //4
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456a@B"); //5
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0909090909"); //6

        //3. Click on "DANG KY" button
        driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();

        //4. Verify error message
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),"Mật khẩu bạn nhập không khớp"); //5
    }

    @Test
    public void TC_06_Register_With_Invalid_Phone_Number(){
        //1. Launch website
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //2. Input data for all fields (exception Email and Confirm Email)
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Automation FC"); //1
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("test@yopmail.com"); //2
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("test@yopmail.com"); //3
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456a@A"); //4
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456a@A"); //5
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("012345743"); //6

        //3. Click on "DANG KY" button
        driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();

        //4.1 Verify error message
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(),"Số điện thoại phải từ 10-11 số."); //5

        //4.2 Verify error message when input phone number > 10 degits
        driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("01234574353453"); //6
        driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(),"Số điện thoại phải từ 10-11 số."); //5

        //5. Clear phone number, input phone do not begin with 0-
        driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("23432");

        //6. Click on "DANG KY" button
        driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();

        //7. Verify error message
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08"); //5

        //8. Clear phone number, input phone contains text
        driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("5675--");

        //9. Click on "DANG KY" button
        driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();

        //10. Verify error message
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(),"Vui lòng nhập con số"); //5

    }



    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
