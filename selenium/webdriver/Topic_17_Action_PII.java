package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;

public class Topic_17_Action_PII {
    WebDriver driver;
    Actions action;
    JavascriptExecutor jsExecutor;
    String dragDropFilePath = System.getProperty("user.dir") + "/dragDrop/drag_and_drop_helper.js";

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        //driver = new EdgeDriver();
        //driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        action = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_Scroll() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));

        //1. Double click the button: "Double click me"
        if (driver.toString().contains("Chrome") || driver.toString().contains("Edge")){
            action.scrollToElement(doubleClickButton).perform();
            Thread.sleep(3000);
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                    driver.findElement(By.xpath("//button[text()='Double click me']")));
            Thread.sleep(2000);
        }

        action.doubleClick(doubleClickButton)
                .pause(Duration.ofSeconds(4))
                .perform();
        //2. Verify text in the text box: "Hello Automation Guys!"
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_02_Drag_Drop_HTML4() throws InterruptedException {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        //1. Put a small circle into a big circle
        WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));
        WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
        Assert.assertEquals(bigCircle.getText(),"Drag the small circle here.");
        action.dragAndDrop(smallCircle,bigCircle).perform();
        Thread.sleep(3000);
        //2. Verify the message "You đi great" is displayed
        Assert.assertEquals(bigCircle.getText(),"You did great!");
        //3. Verify the background color is changed to blue
        Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex().toUpperCase(),
                "#03A9F4");
    }

    @Test
    public void TC_03_Drag_Drop_HTML5_Selenium_4x() throws InterruptedException {
        //Run passed this case with: Edge/Chrome
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        //1. Put a small circle into a big circle
        WebElement columnA = driver.findElement(By.cssSelector("div#column-a"));
        WebElement columnB = driver.findElement(By.cssSelector("div#column-b"));

        action.dragAndDrop(columnA,columnB).perform();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

        action.dragAndDrop(columnB,columnA).perform();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"B");
    }

    @Test
    public void TC_04_Drag_Drop_HTML5_JQuery() throws InterruptedException, IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        String jqueryContent = getContentFile(dragDropFilePath);

        //1. Put a small circle into a big circle
        String sourceCss = "div#column-a";
        String targetCss = "div#column-b";

        jqueryContent = jqueryContent + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
        jsExecutor.executeScript(jqueryContent);
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

        jsExecutor.executeScript(jqueryContent);
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"B");
    }

    @Test
    public void TC_05_Drag_Drop_HTML5_Robot() throws InterruptedException, AWTException {
        //Run passed this case with
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        //1. Put a small circle into a big circle
        dragAndDropHTML5ByXpath("//div[@id='column-a']","//div[@id='column-b']");
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

        dragAndDropHTML5ByXpath("//div[@id='column-a']","//div[@id='column-b']");
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"B");
    }



    @AfterClass
    public void afterClass(){
       // driver.quit();
    }


    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }

    public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

        WebElement source = driver.findElement(By.xpath(sourceLocator));
        WebElement target = driver.findElement(By.xpath(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        Dimension sourceSize = source.getSize();
        Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}
