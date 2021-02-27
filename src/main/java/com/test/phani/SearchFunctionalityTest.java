package com.test.phani;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.test.phani.pagerepository.*;
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
    private com.test.phani.pagerepository.HomePage homePage = new HomePage();
    private com.test.phani.pagerepository.MyAccountPage myAccountPage = new MyAccountPage();
    private com.test.phani.pagerepository.LogInPage logInPage = new LogInPage();
    private com.test.phani.pagerepository.OrdersPage ordersPage = new OrdersPage();
    private String orderXpath;



    @Given("User in login page")
    public void launchApplication() {
        WebDriverInstance instance = new WebDriverInstance();
        sparkReport = new ExtentHtmlReporter("result.html");
        report = new ExtentReports();
        report.attachReporter(sparkReport);
        driver = instance.getDriver("chrome");
        logInPage.verifyLaunchPage(test,driver);
    }

    @When("user successfully logged in with {string} and {string}")
    public void user_successfully_logged_in_with_and(String userName, String pwd) {
        test = report.createTest("Order T-shirt Test ANd Modify User name","Order T-shirt in automation practice test application");
        logInPage.login(driver);
        logInPage.validateLogin(driver,userName,pwd);
    }

    @Then("home page should displayed")
    public void verifyHomePage() {
        homePage.verifyHomePage(driver);
    }

    @Then("search and add product to cart")
    public void searchAndAddTShirtToCart() {
        homePage.clickMenuSection(driver,test,"T-Shirt");
        List<WebElement> list = homePage.returnProductsResultSet(driver,test);
        for(WebElement ele: list){
            if(homePage.checkInStock(ele)){
                homePage.addToCart(driver,test,ele);
                break;
            }
        }
    }

    @Then("checkout the cart")
    public void checkout() {
        //Click on proceed to check out
        homePage.clickElement(driver,test,driver.findElement(By.xpath(homePage.proceedToCheckOutBtn)),"Checkout");
        //Click on proceed to check out Summary section in Orders page
        ordersPage.clickElement(driver,test,driver.findElement(By.xpath(ordersPage.proceedToCheckOutOrderBtn)),"Checkout Summary");
        //Click on proceed to check out Address section in Orders page
        ordersPage.clickElement(driver,test,driver.findElement(By.xpath(ordersPage.proceedToCheckOutOrderBtn)),"Checkout Address");
        //Check the shipping terms
        ordersPage.selectElement(driver,test,driver.findElement(By.xpath(ordersPage.shippingTermsChkBox)),"Check shipping terms");
        //Click on proceed to check out Shipping section in Orders page
        ordersPage.clickElement(driver,test,driver.findElement(By.xpath(ordersPage.proceedToCheckOutOrderBtn)),"Checkout Shipping");
        //Click on pay by check
        ordersPage.clickElement(driver,test,driver.findElement(By.xpath(ordersPage.payByCheck)),"Pay By Check");
        //Click on order confirmation
        ordersPage.clickElement(driver,test,driver.findElement(By.xpath(ordersPage.confirmOrder)),"Order confirmation");

        if(ordersPage.verifyOrderSuccess(driver)){
            test.log(Status.PASS,"Successfully placed the order");
        }else{
            test.log(Status.FAIL,"Failed to placed the order");
        }

        orderXpath = "//a[contains(text(),'"+ ordersPage.getOrderReference(driver) +"')]";
    }

    @Then("validate Order in Orders History")
    public void validateOrders() {
        //Click on User Icon
        homePage.clickElement(driver,test,driver.findElement(By.xpath(homePage.userName)),"User Icon");
        //Click on Order history and details
        homePage.clickElement(driver,test,driver.findElement(By.xpath(homePage.orderHistoryBtn)),"Order history and details");
        if(OrdersHistory.validateOrders(driver,orderXpath)){
            test.log(Status.PASS,"Order displayed successfully on orders History page");
        }else{
            test.log(Status.FAIL,"Order not displayed on orders History page");
        }
        driver.quit();
    }

    @Then("click on personal information")
    public void click_on_personal_information() {
        homePage.clickElement(driver,test,driver.findElement(By.xpath(homePage.personalInfoBtn)),"Personal Information");
    }

    @Then("edit user name {string} with password {string}")
    public void edit_user_name(String value, String pwd) {
        myAccountPage.sendKeys(driver,test,driver.findElement(By.id(myAccountPage.firstName)),value);
        myAccountPage.sendKeys(driver,test,driver.findElement(By.id(myAccountPage.oldPwd)),pwd);
    }

    @Then("Save changes")
    public void save_changes() {
        myAccountPage.clickElement(driver,test,driver.findElement(By.xpath(myAccountPage.saveBtn)),"Save update");
    }


    @Then("validate changes {string}")
    public void validate_changes(String updatedName) {
        if(myAccountPage.verifySuccess(driver)
                && homePage.getTextOfElement(driver,driver.findElement(By.xpath(homePage.userName))).contains(updatedName)){
            test.log(Status.PASS,"User name updated successfully");
        }else{
            test.log(Status.FAIL,"User name update failed");
        }
        report.flush();
        driver.quit();
    }
}
