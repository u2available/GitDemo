package Utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static Core.Driver.getDefaultDriver;
import static Core.Driver.getDriver;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

public class BaseMethods {
    private  static BaseMethods baseMethods=null;
    public static BaseMethods getBaseMethods() {
        if (baseMethods == null)
        {
            // To make thread safe
            synchronized (BaseMethods.class)
            {
                // check again as multiple threads
                // can reach above step
                if (baseMethods==null)
                    baseMethods = new BaseMethods();
            }
        }
        return baseMethods;
    }
    public static void waitForElementToBeDisplayed(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(getDefaultDriver(), ofSeconds(10));
            ExpectedCondition<Boolean> elementDisplayed = arg0 -> {
                try {
                    element.isDisplayed();
                    return true;
                } catch (Exception e) {
                    return false;
                }
            };
            wait.until(elementDisplayed);
        } catch (Exception ex) {
            Logs.error(String.format("Exception in waiting for Element %s", ex.getMessage()));
            Assert.fail(String.format("Exception in waiting for Element %s", ex.getMessage()));
        }
    }

    public void waitForElementToBeDisplayed(By element) {
        try {
            WebDriverWait wait = new WebDriverWait(getDefaultDriver(), ofMillis(120));
            ExpectedCondition<Boolean> elementDisplayed = arg0 -> {
                try {
                    getDriver().findElement(element).isDisplayed();
                    return true;
                } catch (Exception e) {
                    return false;
                }
            };
            wait.until(elementDisplayed);
        } catch (Exception ex) {
            Logs.error(String.format("Exception in waiting for Element %s", ex.getMessage()));
            Assert.fail(String.format("Exception in waiting for Element %s", ex.getMessage()));
        }
    }

    public static void waitForVisibilityOfElement(WebElement el) {
        try {
            FluentWait wait = new WebDriverWait(getDefaultDriver(), ofMillis(3000)).
                    pollingEvery(ofMillis(3000)).
                    ignoring(NoSuchElementException.class).
                    ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.visibilityOf(el));
        } catch (Exception ex) {
            Logs.error(String.format("Exception in waiting for Element %s", ex.getMessage()));
            Assert.fail(String.format("Exception in waiting for Element %s", ex.getMessage()));
        }
    }

    public static void waitForVisibilityOfElement(By el) {
        try {
            FluentWait wait = new WebDriverWait(getDefaultDriver(), ofSeconds(10)).
                    pollingEvery(ofMillis(1000)).
                    ignoring(NoSuchElementException.class).
                    ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(el)));
        } catch (Exception ex) {
            Logs.error(String.format("Exception in waiting for Element %s", ex.getMessage()));
            Assert.fail(String.format("Exception in waiting for Element %s", ex.getMessage()));
        }
    }

    public static void waitForInVisibilityOfElement(WebElement el) {
        try {
            FluentWait wait = new WebDriverWait(getDefaultDriver(), ofMillis(120)).
                    pollingEvery(ofMillis(1000)).
                    ignoring(NoSuchElementException.class).
                    ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.invisibilityOf(el));
        } catch (Exception ex) {
            Logs.error(String.format("Exception in waiting for Element %s", ex.getMessage()));
            Assert.fail(String.format("Exception in waiting for Element %s", ex.getMessage()));
        }
    }
    public static void waitForInVisibilityOfElement(By el) {
        try {
            FluentWait wait = new WebDriverWait(getDefaultDriver(), ofMillis(120)).
                    pollingEvery(ofMillis(1000)).
                    ignoring(NoSuchElementException.class).
                    ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.invisibilityOf(getDriver().findElement(el)));
        } catch (Exception ex) {
            Logs.error(String.format("Exception in waiting for Element %s", ex.getMessage()));
            Assert.fail(String.format("Exception in waiting for Element %s", ex.getMessage()));
        }
    }

    public void sendValue(WebElement el, String value) {
        try {
            el.sendKeys(value);
        } catch (Exception ex) {
            Logs.error(String.format("Exception occurred "+ex.getMessage()));
            Assert.fail(String.format("Failed with Exception "+ex.getMessage()));
        }
    }
    public void sendValue(By el, String value) {
        try {
            getDefaultDriver().findElement(el).sendKeys(value);
        } catch (Exception ex) {
            Logs.error(String.format("Exception occurred "+ex.getMessage()));
            Assert.fail(String.format("Failed with Exception "+ex.getMessage()));
        }
    }

    public static void scrollDown(WebElement element) {
        int numberOfTimes = 10;
        Dimension dimension = getDefaultDriver().manage().window().getSize();
        int windowsHeight = dimension.getHeight();
        int scrollStart = (int) (windowsHeight * 0.7);
        int scrollEnd = (int) (windowsHeight * 0.4);
        int elementLocationOffset = windowsHeight-500;
        int elementLocationY;
        for (int i = 0; i < numberOfTimes; i++) {
            try{
                elementLocationY= element.getLocation().y;
                if (elementLocationY < elementLocationOffset){
                    i = numberOfTimes;
                    System.out.println("Element available.");
                }else {
                    scroll(scrollStart, scrollEnd);
                    System.out.println("Element not available. Scrolling...");
                }}catch (Exception ex){
                scroll(scrollStart, scrollEnd);
                System.out.println("Element not available. Scrolling...");
            }

        }
    }
    public static void scroll(int scrollStart, int scrollEnd)
    {
        JavascriptExecutor js = (JavascriptExecutor) getDefaultDriver();
        js.executeScript("window.scrollTo({0}, {1})", scrollStart, scrollEnd);
    }
    public void moveToElement(WebElement el) {
        try {
            Actions action = new Actions(getDriver());
            waitForElementToBeDisplayed(el);
            action.moveToElement(el).perform();
        } catch (Exception ex) {
            Logs.error(String.format("Element is not visible"+ex.getMessage()));
        }

    }
    public void moveToElement(By el) {
        try {
            Actions action = new Actions(getDefaultDriver());
            waitForElementToBeDisplayed(el);
            action.moveToElement(getDefaultDriver().findElement(el)).perform();
        } catch (Exception ex) {
            Logs.error(String.format("Element is not visible "+ex.getMessage()));
        }

    }
    public void jsClick(By el) {
        try {
            WebDriver driver = getDefaultDriver();
            waitForVisibilityOfElement(el);
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].click();", driver.findElement(el));
        } catch (Exception ex) {
            Logs.error(String.format("Element is not clickable "+ex.getMessage()));
            Assert.fail(String.format("Element is not clickable "+ex.getMessage()));
        }
    }
    public void jsClick(WebElement el) {
        try {
            WebDriver driver = getDefaultDriver();
            waitForVisibilityOfElement(el);
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].click();", el);
        } catch (Exception ex) {
            Logs.error(String.format("Element is not clickable "+ex.getMessage()));
            Assert.fail(String.format("Element is not clickable "+ex.getMessage()));
        }
    }
    public boolean compareTexts(By el, String expectedValue) {
        boolean flag = false;
        try {
            waitForElementToBeDisplayed(el);
            String actualValue = getDefaultDriver().findElement(el).getText();
            if (expectedValue == actualValue){
                flag = true;
            }
        } catch (Exception ex) {
            flag = false;
            Logs.error(String.format("Actual value is not matching with Expected value, Exception is : "+ex.getMessage()));
        }
        return flag;
    }
    public boolean compareTexts(WebElement el, String expectedValue) {
        boolean flag = false;
        try {
            waitForElementToBeDisplayed(el);
            String actualValue = el.getText();
            if (expectedValue == actualValue){
                flag = true;
            }
        } catch (Exception ex) {
            Logs.error(String.format("Actual value is not matching with Expected value, Exception is : "+ex.getMessage()));
        }
        return flag;
    }
    public static String constructXpath(String xpath, String replacingString) { /* xpath format should be similar to this :: //*[@attribute='\"%s\"'] */
        return String.format(xpath, replacingString);
    }
    public void selectFromDropdown(By el, String value) {
        try {
            waitForElementToBeDisplayed(el);
            Select dropdown = new Select(getDefaultDriver().findElement(el));
            dropdown.selectByValue(value);
        } catch (Exception ex) {
            Logs.error(String.format("Failed with Exception : "+ex.getMessage()));
        }
    }
    public void selectFromDropdown(By el, int index) {
        try {
            waitForElementToBeDisplayed(el);
            Select dropdown = new Select(getDefaultDriver().findElement(el));
            dropdown.selectByIndex(index);
        } catch (Exception ex) {
            Logs.error(String.format("Failed with Exception : "+ex.getMessage()));
        }
    }
    public static String getText(By el) {
        WebDriver driver = getDefaultDriver();
        waitForVisibilityOfElement(el);
        return driver.findElement(el).getText();
    }
    public static String getCurrentURL() {
        String currentURL = getDefaultDriver().getCurrentUrl();
        return currentURL;
    }
    public static void waitForElementAndTypeText(By el, String textToType) {
        try {
            waitForVisibilityOfElement(el);
            WebElement element = getDefaultDriver().findElement(el);
            element.click();
            element.clear();
            element.sendKeys(textToType);
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }
    public static boolean isElementDisplayed(By el) {
        boolean status = false;
        try {
            if (getDriver().findElement(el).isDisplayed()) {
                status = true;
            }
        } catch (NoSuchElementException ex) {

        } finally {
            return status;
        }
    }
    public static void waitForElementAndClick(WebElement el) {
        try {
            waitForVisibilityOfElement(el);
            el.click();
        } catch (Exception ex) {
            Logs.error(String.format("Exception in Clicking on Element %s", ex.getMessage()));
            Assert.fail(String.format("Exception in Clicking on Element %s", ex.getMessage()));
        }
    }
    public void closeActiveWindow() {
        try {
            getDefaultDriver().close();
        } catch (Exception ex) {
            Logs.error(String.format("Window does not exists, Exception : "+ex.getMessage()));
        }
    }
    public String getTitle(By el) {
        String title="";
        try {
            title= getDefaultDriver().getTitle();
        } catch (Exception ex) {
            Logs.error(String.format("Failed with Exception : "+ex.getMessage()));
        }
        return title;
    }

    public String getCustomDateValue(int numberOfDays) throws ParseException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String str = formatter.format(date);
        System.out.print("Current date: "+str);
        Calendar c = Calendar.getInstance();
        c.setTime(formatter.parse(str));
        c.add(Calendar.DATE, numberOfDays);  // number of days to add
        str = formatter.format(c.getTime());
        System.out.print("Current date 2: "+str);
        return str;
    }

    public  String getCurrentDate(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = formatter.format(date);
        System.out.print("Current date: "+currentDate);
        return currentDate;
    }
}