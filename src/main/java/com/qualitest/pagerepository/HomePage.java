package com.qualitest.pagerepository;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qualitest.core.ActionHelper;
import com.qualitest.core.Helper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class HomePage extends ActionHelper {

    public static String signOutBtn = "//a[@title='Log me out']";
    public static String tshirtMenuLnk = "//li[3]/a[@title='T-shirts']";
    public static String womenMenuLnk = "//li[1]/a[@title='Women']";
    public static String dressesMenuLnk = "(//ul/li[2]/a[@title='Dresses'])[2]";
    public static String productDisplayEle = "//div[@class='left-block']";
    public static String productInStock = "//div[@class='right-block']//link[@itemprop='availability']/..";
    public static String addToCartBtn = "//span[text()='Add to cart']";
    public static String proceedToCheckOutBtn = "//span[contains(text(),'Proceed to checkout')]";
    public static String successMsgEle = "//i[@class='icon-ok']";
    public static String userName = "//div[@class='header_user_info']//span";
    public static String orderHistoryBtn = "//span[text()='Order history and details']";
    public static String personalInfoBtn = "//span[text()='My personal information']";


    public void verifyHomePage(WebDriver driver ){
        if(Helper.checkElementExists(driver.findElement(By.xpath(signOutBtn)))){
        }
    }

    public void clickMenuSection(WebDriver driver, ExtentTest test, String menuOption){
        switch(menuOption.toLowerCase()){
            case "t-shirt":
                if(Helper.checkElementExists(driver.findElement(By.xpath(tshirtMenuLnk)))){
                    driver.findElement(By.xpath(tshirtMenuLnk)).click();
                }else{
                    test.log(Status.FAIL,"Menu Item T-Shirt not displayed");
                }
                break;
            case "women":
                if(Helper.checkElementExists(driver.findElement(By.xpath(womenMenuLnk)))){
                    driver.findElement(By.xpath(womenMenuLnk)).click();
                }else{
                    test.log(Status.FAIL,"Menu Item Women not displayed");
                }
                break;
            case "dresses":
                if(Helper.checkElementExists(driver.findElement(By.xpath(dressesMenuLnk)))){
                    driver.findElement(By.xpath(dressesMenuLnk)).click();
                }else{
                    test.log(Status.FAIL,"Menu Item Dresses not displayed");
                }
                break;
        }
    }

    public List<WebElement> returnProductsResultSet(WebDriver driver,ExtentTest test){
        List<WebElement> elements = new LinkedList<>();
        if(! driver.findElements(By.xpath(productInStock)).isEmpty()){
            test.log(Status.INFO,"Products to buy available..");
            elements = driver.findElements(By.xpath(productInStock));
        }
        return elements;
    }

    public boolean checkInStock(WebElement product ){
        if(product.getText() != "" && product.getText().toLowerCase().contains("in stock"))
            return true;
        return false;
    }

    public void addToCart(WebDriver driver, ExtentTest test,WebElement product ){
        Actions actions = new Actions(driver);
        actions.moveToElement(product).perform();
        if(Helper.checkElementExists(driver.findElement(By.xpath(addToCartBtn)))){
            actions.moveToElement(driver.findElement(By.xpath(addToCartBtn))).click().perform();
        }
        if(Helper.checkElementExists(driver.findElement(By.xpath(successMsgEle)))){
            test.log(Status.INFO,"Product added to cart successfully..");
        }
    }

    public String getTextOfElement(WebDriver driver, WebElement ele){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOf(ele));
        if(Helper.checkElementExists(ele)){
            return ele.getText();
        }else{
            return "";
        }
    }


}
