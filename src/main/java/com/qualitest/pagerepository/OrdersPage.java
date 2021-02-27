package com.qualitest.pagerepository;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qualitest.core.ActionHelper;
import com.qualitest.core.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrdersPage extends ActionHelper {

    public static String proceedToCheckOutOrderBtn = "(//span[contains(text(),'Proceed to checkout')])[2]";
    public static String shippingTermsChkBox = "//input[@id='cgv']";
    public static String payByCheck = "//a[contains(text(),'Pay by check')]";
    public static String confirmOrder = "//span[contains(text(),'I confirm my order')]";
    public static String orderConfirmation = "//p[@class='alert alert-success']";
    public static String orderReference = "//div[@class='box order-confirmation']";

    public void selectElement(WebDriver driver,ExtentTest test,WebElement ele,String eleName){
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", ele);
        }catch(Exception e){
            test.log(Status.FAIL,"Click operation on element " + eleName + " failed");
        }
    }

    public static boolean verifyOrderSuccess(WebDriver driver){
        if(Helper.checkElementExists(driver.findElement(By.xpath(orderConfirmation)))
                && driver.findElement(By.xpath(orderConfirmation)).getText().contains("is complete")){
            return true;
        }else{
            return false;
        }
    }

    public static String getOrderReference(WebDriver driver){
        String orderName="";
        if(Helper.checkElementExists(driver.findElement(By.xpath(orderReference)))){
            String text = driver.findElement(By.xpath(orderReference)).getText();
            String[] reference = text.split("reference");
            orderName = ((reference[1].split("\\."))[0]).trim();
        }
        return orderName;
    }
}
