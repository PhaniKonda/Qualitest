package com.qualitest.core;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class ActionHelper {

    public void sendKeys(WebDriver driver, ExtentTest test, WebElement ele, String value){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOf(ele));
        if(Helper.checkElementExists(ele)){
            ele.clear();
            ele.sendKeys(value);
        }else{
            test.log(Status.FAIL,"Cannot set text in element");
        }
    }

    public void clickElement(WebDriver driver,ExtentTest test,WebElement ele,String eleName){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOf(ele));
        if(Helper.checkElementExists(ele)){
            ele.click();
        }else{
            test.log(Status.FAIL,"Click operation on element " + eleName + " failed");
        }
    }
}
