package Pages;
import Core.PageFactoryBase;
import Utils.BaseMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.ParseException;

// To include page factory helper extension
public class HomePage extends PageFactoryBase {
    private static volatile HomePage homePage = null;
    BaseMethods baseMethods = BaseMethods.getBaseMethods();
     HomePage()
     {
        super(driver);
     }
    /**
     * Double Checked Locking with Singleton instantiation for thread safe operation
     *
     * @return
     */
    public static HomePage getHomePage()
    {
        if (homePage == null)
        {
            // To make thread safe
            synchronized (HomePage.class)
            {
                // check again as multiple threads
                // can reach above step
                if (homePage == null)
                    homePage = new HomePage();
            }
        }
        return homePage;
    }
    String DepartDay = "21";
    String ReturnDay = "23";
    String Month = "June2023";
    @FindBy (xpath="//input[@id='returnDateRequiredYes']")
            public WebElement ClickRoundTrip;
    @FindBy (xpath="//input[@id='origin']")
            public WebElement From;
    @FindBy (xpath="//b[text()='DEL']")
            public WebElement InputFrom;
    @FindBy (xpath="//input[@id='destination']")
            public WebElement To;
    @FindBy (xpath="//*[@id=\"eac-container-destination\"]/ul/li[2]/div/b")
            public WebElement InputTo;
    @FindBy (xpath="//*[@id=\"dateNew\"]")
            public WebElement ClickDepartDate;
    @FindBy (xpath="//*[@id=\"dateNewRet\"]")
            public WebElement ClickReturnDate;
    @FindBy (xpath="//input[@id='traveller']")
            public WebElement ClickTraveller;
    @FindBy (xpath="(//i[@class='zmdi zmdi-hc-2x zmdi-plus-circle'])[1]")
            public WebElement ClickAdultButton;
    @FindBy (xpath="//select[@id='classi']")
            public WebElement ClickCabin;
    @FindBy (xpath="//*[@id=\"classi\"]/option[2]")
            public WebElement ClickBusiness;
    @FindBy (xpath="//input[@value='Done']")
            public WebElement ClickDone;
    @FindBy (xpath="//*[@id='search_btn_home']")
            public WebElement ClickSearchButton;
    @FindBy (xpath = "(//div[@class='picker__header'])[1]")
            public WebElement ActualMonth;
    @FindBy (xpath = "//div[@id='dateNew_root']//div[@class='picker__holder']//div[@class='picker__nav--next']")
            public WebElement RightArrow;
    @FindBy (xpath = "//div[@class=\"picker picker--opened picker--focused\"]//table/tbody/tr/td/div[@class='picker__day picker__day--infocus' and contains(text(),'21')]")
            public WebElement ActualDate;
    @FindBy (xpath = "//div[@class=\"picker picker--opened picker--focused\"]//table/tbody/tr/td/div[@class='picker__day picker__day--infocus' and text()='23']")
    public WebElement ActualReturnDate;
    @FindBy (xpath = "(//div[@aria-label='24/05/2023'])[1]")
    public WebElement currentDate;
    @FindBy (xpath = "(//div[@aria-label='26/05/2023'])[2]")
    public WebElement ReturnDate;
    public void ClickRoundTrip()
    {
        baseMethods.waitForElementAndClick(ClickRoundTrip);
    }
    public void SelectCity()
    {
        baseMethods.waitForElementAndClick(From);
        baseMethods.waitForElementAndClick(InputFrom);
        baseMethods.waitForElementAndClick(To);
        baseMethods.waitForElementAndClick(InputTo);
    }
    public void SelectDate() throws InterruptedException, ParseException {
        baseMethods.waitForElementAndClick(ClickDepartDate);
        Thread.sleep(1000);
        baseMethods.waitForElementAndClick(getDefaultDriver().findElement(By.xpath("(//div[@aria-label='"+baseMethods.getCustomDateValue(1)+"'])[1]")));
        baseMethods.waitForElementAndClick(ClickReturnDate);
        Thread.sleep(1000);
        baseMethods.waitForElementAndClick(getDefaultDriver().findElement(By.xpath("(//div[@aria-label='"+baseMethods.getCustomDateValue(5)+"'])[2]")));
    }
    public void SelectTraveller()
    {
        ClickTraveller.click();
        baseMethods.waitForElementAndClick(ClickAdultButton);
    }
    public void SelectCabin()
    {
        ClickCabin.click();
        baseMethods.waitForElementAndClick(ClickBusiness);
        ClickDone.click();
    }
    public void ClickonSearchButton()
    {
        baseMethods.waitForElementAndClick(ClickSearchButton);
    }
}

