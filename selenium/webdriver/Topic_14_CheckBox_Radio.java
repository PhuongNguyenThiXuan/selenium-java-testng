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
import java.util.List;

public class Topic_14_CheckBox_Radio {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_KendoUI() throws InterruptedException {
        //1. Access url
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        Thread.sleep(3000);

        By dualZone = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                driver.findElement(By.cssSelector("div#demo-runner")));
        Thread.sleep(2000);

        //2. Select checkbox: "Dual-zone air conditioning"
        if(!driver.findElement(dualZone).isSelected()){
            driver.findElement(dualZone).click();
        }
        Thread.sleep(2000);
        //3. Verify selected
        Assert.assertTrue(driver.findElement(dualZone).isSelected());

        //4. Un-select "Dual-zone air conditioning" then verify un-selected
        if(driver.findElement(dualZone).isSelected()){
            driver.findElement(dualZone).click();
        }
        Thread.sleep(2000);
        Assert.assertFalse(driver.findElement(dualZone).isSelected());

        //5. Access url
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        Thread.sleep(4000);

        By twoPetrol = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("div#demo-runner")));
        Thread.sleep(2000);
        //6. Select radio: "2.0 Petrol, 147kW"
        driver.findElement(twoPetrol).click();
        //7. Verify selected. If not, select it again
        if(!driver.findElement(twoPetrol).isSelected()){
            driver.findElement(twoPetrol).click();
        }
    }


    @Test
    public void TC_02_Radio() throws InterruptedException {
        //1. Access url
        driver.get("https://material.angular.io/components/radio/examples");
        By summer = By.xpath("//label[text()='Summer']/preceding-sibling::div");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("example-viewer#radio-ng-model")));
        Thread.sleep(2000);
        //2. Click on: "Summer"
        driver.findElement(summer).click();
        //3. Verify selected. If not, select it again
        if(!driver.findElement(summer).isSelected()){
            driver.findElement(summer).click();
        }
        //4. Access url
        driver.get("https://material.angular.io/components/checkbox/examples");
        //5. Select checkbox then Verify checkbox selected
        selectCheckbox("//h2[text()='Checkbox configuration']/following-sibling::section/mat-checkbox", "Checked");
        selectCheckbox("//h2[text()='Checkbox configuration']/following-sibling::section/mat-checkbox","Indeterminate");

    }

    private void selectCheckbox(String checkboxList,String value) throws InterruptedException {
        List<WebElement> allCheckbox = driver.findElements(By.xpath(checkboxList));

        for (WebElement checkbox : allCheckbox){
            if(checkbox.getText().trim().equals(value)){
                checkbox.click();
                Thread.sleep(2000);
                Assert.assertFalse(checkbox.isSelected());
                System.out.println("Checkbox: " + value + " is selected");
                break;
            }else{
                System.out.println("Checkbox: " + value + " is not selected");
            }
        }
    }

    @Test
    public void TC_03_Select_All() throws InterruptedException {
        //1. Access url
        driver.get("https://automationfc.github.io/multiple-fields/");
        Thread.sleep(3000);
        //2. Select all checkbox
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                driver.findElement(By.xpath("//label[text()=' Have you ever had (Please check all that apply) ']/parent::li")));

        List<WebElement> allCheckbox = driver.findElements(By.cssSelector("input.form-checkbox"));
        for (WebElement checkbox : allCheckbox){
            if(!checkbox.isSelected()) {
                checkbox.click();
            }
        }
        //3. Verify all checkbox is checked
        for (WebElement checkbox : allCheckbox){
            Assert.assertTrue(checkbox.isSelected());
        }
        //4. Select only "Heart Attack" checkbox
        for (WebElement checkbox : allCheckbox){
            if(checkbox.isSelected()) {
                checkbox.click();
            }
        }
        driver.findElement(By.xpath("//label[text()=' Heart Attack ']/preceding-sibling::input")).click();
        //5. Verify this checkbox is selected
        for (WebElement checkbox : allCheckbox){
            if(checkbox.getText().trim().equals("Heart Attack")) {
                Assert.assertTrue(checkbox.isSelected());
            }
        }

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
