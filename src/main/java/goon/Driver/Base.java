package goon.Driver;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class Base {
    WebDriver driver;
    Properties prop;
    JavascriptExecutor jse;
    public List<String> findValueInTable(WebElement tableXPath, String valueToFind) {
        // Locate the table

        // Locate rows of the table
        List<WebElement> rows = tableXPath.findElements(By.tagName("tr"));
        System.out.println(rows.size());
        List<String> values=new LinkedList<>();
        // Iterate through each row
        for (int i=0; i< rows.size(); i++) {
            // Locate columns of the current row
            List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));

            // Iterate through each column
            for (WebElement column : columns) {
                // Check if the cell contains the desired value
                System.out.println(column.getText());
                if (column.getText().equals(valueToFind)) {
                    System.out.println("value matched!");
                    values.add(column.getText());
                     // Value found
                }
            }
        }

        return values;
    }

    public int findRowWithGivenColumn(WebElement tableXPath, String valueToFind){
        List<WebElement> rows = tableXPath.findElements(By.tagName("tr"));
        int columnIndex=0;
        int rowIndex = 0;
        // Iterate through each row
        for (rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            WebElement row = rows.get(rowIndex);
            // Locate columns of the current row
            List<WebElement> columns = row.findElements(By.tagName("td"));
            if (columns.size() > columnIndex) {
                // considering first column to have the value to reduce the number of search
               // System.out.println(columns.get(columnIndex).getText());
                if (columns.get(columnIndex).getText().equals(valueToFind)) {
                    return rowIndex; // Return the row number (1-based index)
                }
            }
        }
        return -1;
    }

    public List<String> getRowDataByIndex(WebElement table, int rowIndex) {
        List<String> rowData = new ArrayList<>();

        // Locate the table

        List<WebElement> rows = table.findElements(By.tagName("tr"));

        if (rowIndex >= 0 && rowIndex < rows.size()) {
            WebElement row = rows.get(rowIndex);
            List<WebElement> cells = row.findElements(By.tagName("td"));

            for (WebElement cell : cells) {
                rowData.add(cell.getText());
            }
        } else {
            throw new IndexOutOfBoundsException("Row index is out of range.");
        }

        return rowData;
    }
    public WebElement clickLinkInRow(WebElement table, int rowIndex)  {
        WebElement link = null;
        // Locate the table
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        if (rowIndex >= 0 && rowIndex < rows.size()) {
            WebElement row = rows.get(rowIndex);
            // Find the link in the row
           link = row.findElement(By.tagName("a"));
            // Click the link
            scrollUp();
//            if(link.isDisplayed())
//             return link;
         //   clickElement(link);

        }
        return link;
    }

    public void clickOnCell(){
        //*[@id="DataTables_Table_0"]/tbody/tr[7]/td[1]
    }
    public void waitForElementToBeVisible(WebElement element) throws IOException {
        prop= new Properties();
        prop.load(new FileInputStream("config.property"));
        WebDriverWait wait = new WebDriverWait(SingleDriver.getDriver(), Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOf(element));
        //log else if the element is not found
    }

    //put this method in commons/wrappers instead
    public void clickElement(WebElement element){
        if(element.isDisplayed()){
            element.click();
        }
        else{
            System.out.println("element is not found"); //keep exception logging

        }
    }
    public void scrollIntoView(WebElement element){
        jse = (JavascriptExecutor)SingleDriver.getDriver();
        jse.executeScript("arguments[0].scrollIntoView()", element);

    }
    public void scrollUp(){
        jse = (JavascriptExecutor)SingleDriver.getDriver();
        jse.executeScript("window.scrollBy(0,-40)");
    }
   public void scrollNClick(WebElement element){
       jse = (JavascriptExecutor)SingleDriver.getDriver();
       scrollIntoView(element);
       jse.executeScript("arguments[0].click();", element);
   }

    public void actionClick(WebElement element){

        Actions actions = new Actions(SingleDriver.getDriver());
        actions.moveToElement(element).click().perform();
        //Thread.sleep(5000);
    }
public void clickBeforeArrow(WebElement element){
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("document.querySelector(element,':before').click();");
}}