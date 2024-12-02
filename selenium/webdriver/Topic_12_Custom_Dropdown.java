package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

//Custom_dropdown: khác Select => Option
public class Topic_12_Custom_Dropdown {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_JQuery() throws InterruptedException {
        //1. Access url
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        //2. Select a speed = Medium
        selectItemInDropDown("span#speed-button","ul#speed-menu li div","Medium");
        //3. Verify selected item
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Medium");
        //4. Select another item: Slower/ Faster
        selectItemInDropDown("span#speed-button","ul#speed-menu li div","Slower");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Slower");

        selectItemInDropDown("span#speed-button","ul#speed-menu li div","Faster");
        verifyItemInDropDownList("span#speed-button span.ui-selectmenu-text","Faster");
    }

    private void verifyItemInDropDownList(String textDisplayed, String textSelected) throws InterruptedException {
        Assert.assertEquals(driver.findElement(By.cssSelector(textDisplayed)).getText(),textSelected);
    }

    private void selectItemInDropDown(String dropdownName, String ListValue, String value) throws InterruptedException {
        driver.findElement(By.cssSelector(dropdownName)).click();
        Thread.sleep(2000);

        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.cssSelector(ListValue)));

        List<WebElement> allItems = driver.findElements(By.cssSelector(ListValue));

        for (WebElement item : allItems){
            if(item.getText().equals(value)){
                item.click();
                break;
            }
        }
    }


    @Test
    public void TC_02_ReactJS() throws InterruptedException {
        //1. Access url
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        //2. Select item
        selectItemInDropDown("div.dropdown","div.menu div","Stevie Feliciano");
        //3. Verify selected item
        verifyItemInDropDownList("div.divider","Stevie Feliciano");
        //4. Select item
        selectItemInDropDown("div.dropdown","div.menu div","Justen Kitsune");
        //5. Verify selected item
        verifyItemInDropDownList("div.divider","Justen Kitsune");
    }

    @Test
    public void TC_03_VueJS() throws InterruptedException {
        //1. Access url
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        //2. Select item1
        selectItemInDropDown("li.dropdown-toggle","ul.dropdown-menu li a","First Option");
        verifyItemInDropDownList("li.dropdown-toggle","First Option");
        //3. Select item2
        selectItemInDropDown("li.dropdown-toggle","ul.dropdown-menu li a","Second Option");
        verifyItemInDropDownList("li.dropdown-toggle","Second Option");
        //4. Select item3
        selectItemInDropDown("li.dropdown-toggle","ul.dropdown-menu li a","Third Option");
        verifyItemInDropDownList("li.dropdown-toggle","Third Option");
    }
    


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}