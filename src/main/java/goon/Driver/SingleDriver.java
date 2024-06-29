package goon.Driver;
import org.openqa.selenium.WebDriver;


public  class SingleDriver {

    public static final ThreadLocal<WebDriver> Localdriver = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return Localdriver.get();
    }

    public static void setDriver(WebDriver driver){
        Localdriver.set(driver);
    }


}
