package com.qualitest.pagerepository;

import com.qualitest.core.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrdersHistory {

    public static boolean validateOrders(WebDriver driver,String xpath){
        if(Helper.checkElementExists(driver.findElement(By.xpath(xpath)))){
            return true;
        }
        return false;
    }
}
