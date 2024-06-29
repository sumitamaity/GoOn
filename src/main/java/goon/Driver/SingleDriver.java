package goon.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public  class SingleDriver {

    public static final ThreadLocal<WebDriver> Localdriver = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return Localdriver.get();
    }

    public static void setDriver(WebDriver driver){
        Localdriver.set(driver);
    }


}
