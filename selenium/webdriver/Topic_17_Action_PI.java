package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Topic_17_Action_PI {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        action = new Actions(driver);
    }

    @Test
    public void TC_01_Hold_1To4(){
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li"));
        //1. Click and hold form 1 to 4
        action.clickAndHold(numbers.get(0)).moveToElement(numbers.get(3))
                .pause(Duration.ofSeconds(2))
                .release().perform();
        //2. Verify list selected
        List<WebElement> numberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(numberSelected.size(),4);

    }


    @Test
    public void TC_02_ClickAndHold_Random(){
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li"));

        String osName = System.getProperty("os.name");
        Keys keys = null;

        if (osName.contains("Windows")){
            keys = Keys.CONTROL;
        }else {
            keys = Keys.COMMAND;
        }

        //Actual Number: 1 3 6 11
        List<String> actualNumber = new ArrayList<String>();
        actualNumber.add("1");
        actualNumber.add("3");
        actualNumber.add("6");
        actualNumber.add("11");

        action.keyDown(keys).perform();
        //1. Click and select random: 1 3 6 11
        action.click(numbers.get(0)).pause(Duration.ofSeconds(1))
                .click(numbers.get(2)).pause(Duration.ofSeconds(1))
                .click(numbers.get(5)).pause(Duration.ofSeconds(1))
                .click(numbers.get(10)).pause(Duration.ofSeconds(1))
                .perform();
        action.keyUp(keys).perform();

        //2. Verify the number selected is correctly
        List<WebElement> selectedNumbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(selectedNumbers.size(),4);

        List<String> expectedNumber = new ArrayList<String>();
        for (WebElement number:selectedNumbers){
            expectedNumber.add(number.getText());
        }
        Assert.assertEquals(actualNumber,expectedNumber);

    }

    @Test
    public void TC_03_Double_Click() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));

        //1. Double click the button: "Double click me"
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                driver.findElement(By.xpath("//button[text()='Double click me']")));
        Thread.sleep(2000);

        action.doubleClick(doubleClickButton)
                .pause(Duration.ofSeconds(4))
                .perform();
        //2. Verify text in the text box: "Hello Automation Guys!"
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");

    }

    @Test
    public void TC_04_RightClick() throws InterruptedException {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        Assert.assertFalse(driver.findElement(By.cssSelector("ul.context-menu-list")).isDisplayed());
        //1. Right click to element: "right click me"
        action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']")))
                .pause(Duration.ofSeconds(2))
                .perform();
        //2. Verify Quit menu is displayed
        Assert.assertTrue(driver.findElement(By.cssSelector("ul.context-menu-list")).isDisplayed());
        //3. Hover on element: Quit
        action.moveToElement(driver.findElement(By.xpath("//span[text()='Quit']/parent::li")))
                .pause(Duration.ofSeconds(2))
                .perform();
        //4. Verify element "Quit" visible + hover with xpath
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
        //5. Click on Quit
        action.click(driver.findElement(By.xpath("//span[text()='Quit']/parent::li"))).perform();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
        //6. Verify Quit is not displayed
        Assert.assertFalse(driver.findElement(By.cssSelector("ul.context-menu-list")).isDisplayed());
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
