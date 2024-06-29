package goon.Pages;

import goon.Driver.Base;
import goon.Driver.SingleDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;


public class Homepage extends Base {
WebDriver driver;
WebDriverWait wait;
@CacheLookup
@FindBy(css = "li.main_nav_trading > a")
WebElement  tradingLinkOnMenu;
@CacheLookup
@FindBy(css="li.menu-stocks > a")
WebElement stocksLinkOnSubMenu;
@CacheLookup
@FindBy(css="div.cookie-modal__defaultBlock")
WebElement cookiePopupDialog;
@CacheLookup
@FindBy(xpath="//button[text()='ACCEPT ALL']")
WebElement acceptAllButton;

@CacheLookup
@FindBy(css="button.toggleLeftNav")
WebElement leftMenuNav;
    @CacheLookup
    @FindBy(xpath="//*[@id='main-nav']/li/a/span[contains(text(),'Trading')]")
    WebElement tradingDownArraow;
    @CacheLookup
    @FindBy(xpath="//*[@id='tradingMenu']/ul/li/a/span[contains(text(),'Stocks')]")
    WebElement tstockLinkInNavMenu;



public Homepage(){
    this.driver= SingleDriver.getDriver();
    if(driver!=null)
     PageFactory.initElements(driver, this);
    else
        System.out.println("Driver is null");  //log the error in a logger
}

public void closePopup() throws IOException {
    if(cookiePopupDialog.isDisplayed()){
        clickElement(acceptAllButton);
    }
}

 public boolean getTitle(String title){
    System.out.println("Title of the home page is -- "+ driver.getTitle());
    return (driver.getTitle().equals(title));

 }

public void clickOnTradingLink() throws IOException {

    if(tradingLinkOnMenu.isDisplayed())
    clickElement(tradingLinkOnMenu);
    else if(leftMenuNav.isDisplayed()) {
        leftMenuNav.click();
        clickElement(tradingDownArraow);
        clickElement(tstockLinkInNavMenu);
    }
}

public void clickOnStocksSubMenuLink() throws IOException {
//add waitfor element
    clickElement(stocksLinkOnSubMenu);
    }


}
