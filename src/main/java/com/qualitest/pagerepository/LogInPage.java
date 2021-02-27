package com.qualitest.pagerepository;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qualitest.core.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogInPage {

    public static String loginBtn = "//a[@title='Log in to your customer account']";
    public static String userName = "email";
    public static String password = "passwd";
    public static String submitBtn = "SubmitLogin";


    public void verifyLaunchPage(ExtentTest test,WebDriver driver ){
        if(Helper.checkElementExists(driver.findElement(By.xpath(loginBtn)))){
        }
    }

    public void login(WebDriver driver){
        WebElement login = driver.findElement(By.xpath(loginBtn));
        login.click();
    }

    public void validateLogin(WebDriver driver, String userEmail, String psswd){
        WebElement user = driver.findElement(By.id(userName));
        WebElement pwd = driver.findElement(By.id(password));
        WebElement submitLogin = driver.findElement(By.id(submitBtn));
        if(Helper.checkElementExists(user) && Helper.checkElementExists(pwd)
                && Helper.checkElementExists(submitLogin) ){
            user.sendKeys(userEmail);
            pwd.sendKeys(psswd);
            submitLogin.click();
        }
    }
}
