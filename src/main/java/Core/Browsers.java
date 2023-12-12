package Core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariOptions;

import static org.openqa.selenium.remote.CapabilityType.*;

public class Browsers {

    private WebDriver driver;

    public WebDriver getChromeDriver() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(PLATFORM_NAME, "Windows 10");
            capabilities.setCapability(BROWSER_VERSION, "latest");
            ChromeOptions options = new ChromeOptions();
            options.merge(capabilities);
            options.addArguments("-incognito");
            options.addArguments("start-maximized");
            options.addArguments("--disable-notifications");
           // options.setHeadless(true);
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            driver= WebDriverManager.chromedriver().capabilities(options).create();
        } catch (SessionNotCreatedException ex) {
            System.out.println("Failed to Create Chrome Session "+ex.getMessage());
        }
        return driver;
    }


    public WebDriver getSafariDriver() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(PLATFORM_NAME, "MAC");
            capabilities.setCapability(BROWSER_VERSION, "latest");
            SafariOptions options = new SafariOptions();
            options.merge(capabilities);
            driver=WebDriverManager.safaridriver().capabilities(options).create();
        } catch (SessionNotCreatedException ex) {
            System.out.println("Failed to Create Safari Session "+ex.getMessage());
        }
        return driver;
    }

    public WebDriver getEdgeDriver() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(PLATFORM_NAME, "Windows 10");
            capabilities.setCapability(BROWSER_VERSION, "latest");
            EdgeOptions options = new EdgeOptions();
            options.merge(capabilities);
            options.addArguments("start-maximized");
            options.addArguments("inprivate");
            driver=WebDriverManager.edgedriver().capabilities(options).create();
        } catch (SessionNotCreatedException ex) {
            System.out.println("Failed to Create Edge Session "+ex.getMessage());
        }
        return driver;
    }
    public WebDriver getFirefoxDriver() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(PLATFORM_NAME, "Windows 10");
            capabilities.setCapability(BROWSER_VERSION, "latest");
            FirefoxOptions options = new FirefoxOptions();
            options.merge(capabilities);
            options.addArguments("-private");
            options.addArguments("start-maximized");
            driver=WebDriverManager.firefoxdriver().capabilities(options).create();
        } catch (SessionNotCreatedException ex) {
            System.out.println("Failed to Create Firefox Session "+ex.getMessage());
        }
        return driver;
    }
}
