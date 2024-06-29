import goon.util.CommonsUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;


public class TC_001_NorwayStock extends BaseTest {
    /*  Note: Exceptions, async waits, dataprovider are not added
    * due to lack of time  */

    @Test
    public void testCase_001_validate_OkraASA_stock_details() throws IOException, InterruptedException
    {
       //close the cookie dialog validate title of the home page
       homepage.closePopup() ;
       Assert.assertTrue(homepage.getTitle("Forex & CFD Trading on Stocks, Indices, Oil, Gold by XM™"),"Title not matched");
       homepage.clickOnTradingLink();     //navigate to stock page validate red stock button
       homepage.clickOnStocksSubMenuLink();
       Assert.assertTrue(stockpage.stockLinkVisible(), "Stock link is not visible");
       stockpage.clickOnNorwayButton();   //Select Norway from country
       //get the value from the table and validate it
       int rowOfValue=stockpage.findValueInStockTable("Orkla ASA (ORK.OL)");
       Assert.assertFalse( rowOfValue< 0, "Table value not found");
       //store the data from stock page table
       Map<String, String> dataMapFromStocksPage= stockpage.mapDatatoHeader(rowOfValue);
       //validate trading conditions page then store the table values to compare later
       Assert.assertTrue(tradingConditionsPage.OrklaASAtradingLinkVisible(), "Orkla ASA link doesn't appear");
       Map<String, String> dataMapFromTradingConditionsPage=tradingConditionsPage.getDataFromLeftAndRightTable();
       //compare values - could be handles for all the data in stock page - lack of time to write exception cases
       Assert.assertTrue(CommonsUtils.compareData(dataMapFromStocksPage, dataMapFromTradingConditionsPage),"values did not match check logs");
    }

}