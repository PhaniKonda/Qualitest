package com.test.phani;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;

public class WebDriverInstance {

    public WebDriver getDriver(String browser){
        WebDriver driver;
        switch(browser.toLowerCase()){
            case "chrome":
            case "google chrome":
                System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                driver.get("http://automationpractice.com/");
                return driver;
        }
        return null;
    }

}
