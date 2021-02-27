package com.qualitest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.qualitest.pagerepository.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchFunctionalityTest{

    private WebDriver driver;
    private ExtentHtmlReporter sparkReport;
    private ExtentReports report;
    private ExtentTest test;
    private HomePage HomePage = new HomePage();
    private MyAccountPage MyAccountPage = new MyAccountPage();
    private LogInPage LogInPage = new LogInPage();
    private OrdersPage OrdersPage = new OrdersPage();
    private String orderXpath;



    @Given("User in login page")
    public void launchApplication() {
        WebDriverInstance instance = new WebDriverInstance();
        sparkReport = new ExtentHtmlReporter("result.html");
        report = new ExtentReports();
        report.attachReporter(sparkReport);
        driver = instance.getDriver("chrome");
        LogInPage.verifyLaunchPage(test,driver);
    }

    @When("user successfully logged in with {string} and {string}")
    public void user_successfully_logged_in_with_and(String userName, String pwd) {
        test = report.createTest("Order T-shirt Test ANd Modify User name","Order T-shirt in automation practice test application");
        LogInPage.login(driver);
        LogInPage.validateLogin(driver,userName,pwd);
    }

    @Then("home page should displayed")
    public void verifyHomePage() {
        HomePage.verifyHomePage(driver);
    }

    @Then("search and add product to cart")
    public void searchAndAddTShirtToCart() {
        HomePage.clickMenuSection(driver,test,"T-Shirt");
        List<WebElement> list = HomePage.returnProductsResultSet(driver,test);
        for(WebElement ele: list){
            if(HomePage.checkInStock(ele)){
                HomePage.addToCart(driver,test,ele);
                break;
            }
        }
    }

    @Then("checkout the cart")
    public void checkout() {
        //Click on proceed to check out
        HomePage.clickElement(driver,test,driver.findElement(By.xpath(HomePage.proceedToCheckOutBtn)),"Checkout");
        //Click on proceed to check out Summary section in Orders page
        OrdersPage.clickElement(driver,test,driver.findElement(By.xpath(OrdersPage.proceedToCheckOutOrderBtn)),"Checkout Summary");
        //Click on proceed to check out Address section in Orders page
        OrdersPage.clickElement(driver,test,driver.findElement(By.xpath(OrdersPage.proceedToCheckOutOrderBtn)),"Checkout Address");
        //Check the shipping terms
        OrdersPage.selectElement(driver,test,driver.findElement(By.xpath(OrdersPage.shippingTermsChkBox)),"Check shipping terms");
        //Click on proceed to check out Shipping section in Orders page
        OrdersPage.clickElement(driver,test,driver.findElement(By.xpath(OrdersPage.proceedToCheckOutOrderBtn)),"Checkout Shipping");
        //Click on pay by check
        OrdersPage.clickElement(driver,test,driver.findElement(By.xpath(OrdersPage.payByCheck)),"Pay By Check");
        //Click on order confirmation
        OrdersPage.clickElement(driver,test,driver.findElement(By.xpath(OrdersPage.confirmOrder)),"Order confirmation");

        if(OrdersPage.verifyOrderSuccess(driver)){
            test.log(Status.PASS,"Successfully placed the order");
        }else{
            test.log(Status.FAIL,"Failed to placed the order");
        }

        orderXpath = "//a[contains(text(),'"+ OrdersPage.getOrderReference(driver) +"')]";
    }

    @Then("validate Order in Orders History")
    public void validateOrders() {
        //Click on User Icon
        HomePage.clickElement(driver,test,driver.findElement(By.xpath(HomePage.userName)),"User Icon");
        //Click on Order history and details
        HomePage.clickElement(driver,test,driver.findElement(By.xpath(HomePage.orderHistoryBtn)),"Order history and details");
        if(OrdersHistory.validateOrders(driver,orderXpath)){
            test.log(Status.PASS,"Order displayed successfully on orders History page");
        }else{
            test.log(Status.FAIL,"Order not displayed on orders History page");
        }
        driver.quit();
    }

    @Then("click on personal information")
    public void click_on_personal_information() {
        HomePage.clickElement(driver,test,driver.findElement(By.xpath(HomePage.personalInfoBtn)),"Personal Information");
    }

    @Then("edit user name {string} with password {string}")
    public void edit_user_name(String value, String pwd) {
        MyAccountPage.sendKeys(driver,test,driver.findElement(By.id(MyAccountPage.firstName)),value);
        MyAccountPage.sendKeys(driver,test,driver.findElement(By.id(MyAccountPage.oldPwd)),pwd);
    }

    @Then("Save changes")
    public void save_changes() {
        MyAccountPage.clickElement(driver,test,driver.findElement(By.xpath(MyAccountPage.saveBtn)),"Save update");
    }


    @Then("validate changes {string}")
    public void validate_changes(String updatedName) {
        if(MyAccountPage.verifySuccess(driver)
                && HomePage.getTextOfElement(driver,driver.findElement(By.xpath(HomePage.userName))).contains(updatedName)){
            test.log(Status.PASS,"User name updated successfully");
        }else{
            test.log(Status.FAIL,"User name update failed");
        }
        report.flush();
        driver.quit();
    }
}
