package Core;

import Pages.HomePage;
import org.openqa.selenium.WebDriver;

public class Driver {
    protected  static WebDriver driver;
    static Browsers browsers=new Browsers();


    public static WebDriver getDefaultDriver() {
        if (driver == null)
        {
            // To make thread safe
            synchronized (HomePage.class)
            {
                // check again as multiple threads
                // can reach above step
                if (driver==null)
                    driver = getDriver();
            }
        }
        return driver;
    }

    public static WebDriver getDriver() {
        PropertiesReader.ReadProperties();
        String browser=Settings.Browser;
        switch (browser.toUpperCase()){
            case "CHROME":
                driver=browsers.getChromeDriver();
                break;
            case "FIREFOX":
                driver=browsers.getFirefoxDriver();
                break;
            case "EDGE":
                driver=browsers.getEdgeDriver();
                break;
            case "SAFARI":
                driver=browsers.getSafariDriver();
                break;
            default:
                System.out.println("Invalid Browser Name");
                break;
        }
        return driver;
    }


}
