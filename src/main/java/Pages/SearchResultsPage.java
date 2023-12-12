package Pages;

import Core.PageFactoryBase;
import Utils.BaseMethods;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.Console;
import java.time.Duration;

public class SearchResultsPage extends PageFactoryBase {

    private static volatile SearchResultsPage searchPage = null;
    BaseMethods baseMethods = BaseMethods.getBaseMethods();

    SearchResultsPage() {
        super(driver);
    }

    public static SearchResultsPage getSearchPage() {
        if (searchPage == null) {
            // To make thread safe
            synchronized (HomePage.class) {
                // check again as multiple threads
                // can reach above step
                if (searchPage == null)
                    searchPage = new SearchResultsPage();
            }
        }
        return searchPage;
    }
    //page elements

    @FindBy(xpath = "//*[@id=\"inlineFormCustomSelect\"]")
    public WebElement SortDropDown;

    @FindBy(xpath = "//select[@id='inlineFormCustomSelect']/option[@value='1']")
    public WebElement LowToHigh;

    @FindBy(id = "preloader_setup")
    public WebElement LoadingIcon;

    @FindBy(xpath = "//input[@id='airline-6E-only']")
    public WebElement FilterByAirline;

    @FindBy(xpath = "(//a[@class='reset_f'])[1]")
    public WebElement ClearFilter;
    @FindBy(xpath = "//a[contains(text(),'I Accept')]")
    public WebElement IAcceptExclusiveDealPopup;
    @FindBy(xpath = "//button/b[contains(text(),'SELECT')]")
    public WebElement SearchButton;

    //Sort The Results
    public void SortTheSearchResults()
    {
        baseMethods.waitForElementAndClick(SortDropDown);
        baseMethods.waitForElementAndClick(LowToHigh);
    }
    public void ClickOnSelect()
    {
        baseMethods.waitForElementAndClick(SearchButton);
        if(isAlertPresent()){
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
        Utils.BaseMethods.waitForElementToBeDisplayed(ReviewFlightDetailsPage.getReviewFlightDetial().Email);
    }

    public String GetPageTitle(){
      return driver.getTitle();
    }


    public boolean isAlertPresent() {
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        }
        catch (NoAlertPresentException | TimeoutException noAlert) {
            return false;
        }
    }
}
