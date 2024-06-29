package goon.Pages;

import goon.Driver.Base;
import goon.Driver.SingleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class StocksPage extends Base {

        WebDriver driver;
        @CacheLookup
        @FindBy(css="[data-value='Norway']")
        WebElement stockNorwayButton;
        @CacheLookup
        @FindBy(css="#tabs2")
        WebElement stockCFDTab;

        @FindBy(css="#DataTables_Table_0 > tbody")
        WebElement norwayStocksTable;
        @CacheLookup
        @FindBy(css="li a.btn.btn-green")
        WebElement readMoreLink;
        @CacheLookup
        @FindBy(css="#DataTables_Table_0_next")
        WebElement nextLink;
//        @CacheLookup
//        @FindBy(css="#DataTables_Table_0_paginate > span > a[data-dt-idx='2']")
//        WebElement nextLink;

        @CacheLookup
        @FindBy(css="#DataTables_Table_0 > thead > tr >th")
        List<WebElement> stockTableHeader;
        @CacheLookup
        @FindBy(css="#DataTables_Table_0_paginate > span > a")
        List<WebElement> nextIterationLink;
        @CacheLookup
        @FindBy(css="button.btn.active[data-value='Norway']")
        WebElement stockNorwayButtonGreen;

       @CacheLookup
       @FindBy(css="li.active > a[href*='stocks']")
        WebElement stockLink;

        public StocksPage(){
                this.driver= SingleDriver.getDriver();
                if(driver!=null)
                        PageFactory.initElements(driver, this);
                else
                        System.out.println("Driver is null");  //log the error in a logger
        }

        public boolean stockLinkVisible() throws IOException {
            waitForElementToBeVisible(stockLink);
            return stockLink.isDisplayed();
        }
        public void clickOnNorwayButton() throws IOException {
                scrollIntoView(stockCFDTab);
                actionClick(stockNorwayButton);
               //check norway button attribute -active
                waitForElementToBeVisible(stockNorwayButtonGreen);
        }
    /* find value in the table if not found in the current page
    * keep checking the next page using next button until page exists
    * finally return the row if found
    * an else block could be added for exception if time was sufficient */
        public int findValueInStockTable(String value) throws IOException, InterruptedException {

                int size = nextIterationLink.size();
                //System.out.println(size);
                int count=0;
                int row=0;
                waitForElementToBeVisible(norwayStocksTable);
                        while(count < size)
                        {
                            row=findRowWithGivenColumn(norwayStocksTable,value);
                            if(row!=-1){
                                System.out.println(value+" found in row num : "+row);
                                break;
                        }else{
                                scrollNClick(nextLink);
                                Thread.sleep(5000);
                                scrollUp();
                                count++;
                        }
                        }
                return row;
        }

     public Map<String, String> mapDatatoHeader(int row) throws IOException, InterruptedException {
            List<String>  dataOfOkra= getRowDataByIndex(norwayStocksTable, row) ;
            List<String> stockTableHeaders= new LinkedList<String>();
            for(WebElement header: stockTableHeader){
                    stockTableHeaders.add(header.getText());
            }
            Map<String, String> okraStockDetails= new HashMap<>();
             if((!stockTableHeaders.isEmpty())&&(dataOfOkra.size()==stockTableHeaders.size()))
              for(int i=0; i< stockTableHeaders.size(); i++){
                     okraStockDetails.put(stockTableHeaders.get(i),dataOfOkra.get(i));
              }
            //doesn't work for small and medium
             WebElement link=clickLinkInRow(norwayStocksTable, row);
             if(link!=null)
                 scrollNClick(link);
             else{
                 WebElement cell =driver.findElement(By.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+row+"]/td[1]"));
                 if(cell.isDisplayed())
                     cell.click();
                 if(readMoreLink.isDisplayed())
                     scrollNClick(readMoreLink);}

             return okraStockDetails;
     }








    }


