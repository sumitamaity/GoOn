package goon.Pages;

import goon.Driver.Base;
import goon.Driver.SingleDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TradingConditionsPage extends Base {
    WebDriver driver;
    @CacheLookup
    @FindBy(css="table.table.pull-left")
    WebElement tradingConditionsLeftTable;
    @CacheLookup
    @FindBy(css="table.table.pull-right")
    WebElement tradingConditionsRightTable;
    @CacheLookup
    @FindBy(xpath="//*[@id='instrument-inner-page']//h2")
    WebElement tradingConditionsHeader;

    @CacheLookup
    @FindBy(css="table.table.pull-left > tbody > tr > td")
    List<WebElement> tradingConditionsLeftKeys;

    @CacheLookup
    @FindBy(css="table.table.pull-left > tbody > tr > td[data-xm-qa-name$=value]")
    List<WebElement> tradingConditionsLeftValues;

    @CacheLookup
    @FindBy(css="table.table.pull-right > tbody > tr > td")
    List<WebElement> tradingConditionsRightKeys;

    @CacheLookup
    @FindBy(css="table.table.pull-right > tbody > tr > td[data-xm-qa-name$=value]")
    List<WebElement> tradingConditionsRightValues;

    @CacheLookup
    @FindBy(css="a[title='Orkla ASA (ORK.OL)']")
    WebElement orklaASAlINK;

    public TradingConditionsPage() {
        this.driver= SingleDriver.getDriver();
        if(driver!=null)
            PageFactory.initElements(driver, this);
        else
            System.out.println("Driver is null");  //log the error in a logger
    }

    public boolean OrklaASAtradingLinkVisible() throws IOException {
        waitForElementToBeVisible(orklaASAlINK);
        return (orklaASAlINK.isDisplayed());
    }
    public boolean tradingConditionsHeaderVisible() throws IOException {
        waitForElementToBeVisible(tradingConditionsHeader);
        return (tradingConditionsHeader.isDisplayed());

    }
//   extract all the data from trading conditions to comapre with the previous page values
    public Map<String, String> getDataFromLeftAndRightTable() throws IOException {
        if(tradingConditionsHeaderVisible())
            scrollIntoView(tradingConditionsHeader);
        waitForElementToBeVisible(tradingConditionsLeftTable);
        waitForElementToBeVisible(tradingConditionsRightTable);
        Map<String, String> data = new HashMap<>();
        for (int i = 0; i < tradingConditionsLeftKeys.size(); i=i+2) {
            //System.out.println(tradingConditionsLeftKeys.get(i).getText()+"   "+tradingConditionsLeftKeys.get(i+1).getText());
            data.put(tradingConditionsLeftKeys.get(i).getText(), tradingConditionsLeftKeys.get(i+1).getText());
        }
        for (int i = 0; i < tradingConditionsRightKeys.size(); i=i+2) {
            //System.out.println(tradingConditionsRightKeys.get(i).getText()+"   "+tradingConditionsRightKeys.get(i+1).getText());
            data.put(tradingConditionsRightKeys.get(i).getText(), tradingConditionsRightKeys.get(i+1).getText());
        }
        System.out.println("list of data found on trading conditions: "+data);
       return data;
    }



}
