package com.qualitest.pagerepository;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qualitest.core.ActionHelper;
import com.qualitest.core.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage extends ActionHelper {
    public static String firstName = "firstname";
    public static String oldPwd = "old_passwd";
    public static String saveBtn = "//span[text()='Save']";
    public static String statusMessage = "//p[@class='alert alert-success']";




    public static boolean verifySuccess(WebDriver driver){
        if(Helper.checkElementExists(driver.findElement(By.xpath(statusMessage)))
                && driver.findElement(By.xpath(statusMessage)).getText().toLowerCase().contains("success")
        ){
            return true;
        }else{
            return false;
        }
    }
}
