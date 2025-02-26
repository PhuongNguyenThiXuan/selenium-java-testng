package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_22_JavascriptExecutor {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    String emailAddress;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        jsExecutor = (JavascriptExecutor) driver;
        emailAddress = "pngtest" + new Random().nextInt(9999) + "@yopmail.com";

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_TechPanda(){
        //1. Access to URL
        navigateToUrlByJS("http://live.techpanda.org/");
        //2. Get domain of this page
        String domain = (String) executeForBrowser("return document.domain");
        Assert.assertEquals(domain,"live.techpanda.org");
        System.out.println(domain);
        //3. Get URL of this page
        String url = (String) executeForBrowser("return document.URL");
        Assert.assertEquals(url,"http://live.techpanda.org/");
        System.out.println(url);
        //4. Open MOBILE page
        clickToElementByJS("//a[contains(text(),'Mobile')]");
        //5. Add SAMSUNG GALAXY into Cart
        clickToElementByJS("//a[contains(text(),'Samsung Galaxy')]/parent::h2/following-sibling::div[contains(@class,'actions')]//button");
        //6. Verify the displayed message: "Samsung Galaxy was added to your shopping cart."
        Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

        String innerText = (String) executeForBrowser("return document.documentElement.innerText;");
        Assert.assertTrue(innerText.contains("Samsung Galaxy was added to your shopping cart."));

        Assert.assertEquals(getElementTextByJS("//li[@class='success-msg']//span"),"Samsung Galaxy was added to your shopping cart.");
        //7. Open Customer Service page
        clickToElementByJS("//a[contains(text(),'Customer Service')]");

        String title = (String) executeForBrowser("return document.title");
        Assert.assertEquals(title,"Customer Service");
        System.out.println(title);
        //8. Scroll to Newsletter textbox
        scrollToElementOnDown("//input[@id='newsletter']");
        //9. Input valid email into Newsletter textbox
        sendkeyToElementByJS("//input[@id='newsletter']",emailAddress);
        //10. Click to Subscribe button
        clickToElementByJS("//button[@title='Subscribe']");
        //11. Verify the displayed text: "Thank you for your subscription."
        Assert.assertEquals(getElementTextByJS("//li[@class='success-msg']//span"),"Thank you for your subscription.");
        //12. Navigation to domain: "https://www.facebook.com/"
        navigateToUrlByJS("https://www.facebook.com/");
        String domainFacebook = (String) executeForBrowser("return document.domain");
        Assert.assertEquals(domainFacebook,"www.facebook.com");
        System.out.println(domainFacebook);
    }


    @Test
    public void TC_02_HTML5_Validation(){
        //1. Access to "https://automationfc.github.io/html5/index.html"
        navigateToUrlByJS("https://automationfc.github.io/html5/index.html");
        //2. Click Submit and verify message under Name textbox
        hightlightElement("//input[@name='submit-btn']");
        driver.findElement(By.name("submit-btn")).click();
        Assert.assertEquals(getElementValidationMessage("//input[@name='fname']"),"Please fill out this field.");
        //3. Input data for Name. Click Submit and verify message under Password textbox
        hightlightElement("//input[@name='fname']");
        driver.findElement(By.name("fname")).sendKeys("Automation Testing");
        Assert.assertEquals(getElementValidationMessage("//input[@name='pass']"),"Please fill out this field.");
        //4. Input data for Password. Click Submit and verify message under Email textbox
        hightlightElement("//input[@name='pass']");
        driver.findElement(By.name("pass")).sendKeys("123456");
        Assert.assertEquals(getElementValidationMessage("//input[@name='em']"),"Please fill out this field.");
        //5. Input invalid data into Email. Click Submit and verify message under Email textbox
        hightlightElement("//input[@name='em']");
        driver.findElement(By.name("em")).sendKeys("123!@#$");
        Assert.assertEquals(getElementValidationMessage("//input[@name='em']"),"Please enter an email address.");
//        //6. Input invalid data into Email. Click Submit and verify message under Email textbox
//        hightlightElement("//input[@name='em']");
//        sendkeyToElementByJS("//input[@name='em']","123@456");
//        Assert.assertEquals(getElementValidationMessage("//input[@name='em']"),"Please enter an email address.");
        //7. Input valid data for Email. Click Submit and verify message under Address textbox
        hightlightElement("//input[@name='em']");
        driver.findElement(By.name("em")).clear();
        driver.findElement(By.name("em")).sendKeys(emailAddress);
        Assert.assertEquals(getElementValidationMessage("//select"),"Please select an item in the list.");
    }


    @Test
    public void TC_03_WorkAround() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/");

        //Click cua WebElement
        //driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")).click();

        //Click bang Action
        //new Actions(driver).click(driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']"))).perform();

        //Click vao element bi an/che
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
        Thread.sleep(3000);

        //Click vao element ma khong can Hover vao Menu/Tooltip
        driver.get("https://www.fahasa.com/");
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Sách Trong Nước']")));
        Thread.sleep(3000);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].textContent;", getElement(locator));
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

}
