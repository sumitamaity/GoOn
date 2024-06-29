import goon.Driver.SingleDriver;
import goon.Pages.Homepage;
import goon.Pages.StocksPage;
import goon.Pages.TradingConditionsPage;
import goon.util.Resolution;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static goon.Driver.SingleDriver.getDriver;

public class BaseTest {
    WebDriver driver;
    Homepage homepage;
    StocksPage stockpage;
    TradingConditionsPage tradingConditionsPage;
    Properties prop;

    @Parameters({"resolution"})
    @BeforeTest
    public void start(String resolutionParam) throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        System.out.println("Running in Size: "+resolutionParam);
        prop= new Properties();
        prop.load(new FileInputStream("config.property"));
        SingleDriver.setDriver(driver);
        getDriver().get(prop.getProperty("XM_HOME"));
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        if(resolutionParam.equalsIgnoreCase("MAXIMUM")){
            getDriver().manage().window().maximize();
        }else {
            Resolution resolution = Resolution.valueOf(resolutionParam);
            getDriver().manage().window().setSize(new Dimension(resolution.getWidth(), resolution.getHeight()));
            getDriver().navigate().refresh();
        }
        homepage = new Homepage();
        stockpage = new StocksPage();
        tradingConditionsPage=new TradingConditionsPage();

    }



    @AfterTest
    public void quit(){
        //add null exception
        getDriver().quit();;
    }
}
