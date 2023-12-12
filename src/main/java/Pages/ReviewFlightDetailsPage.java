package Pages;
import Core.PageFactoryBase;
import Utils.BaseMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReviewFlightDetailsPage extends PageFactoryBase
{
    private static volatile ReviewFlightDetailsPage reviewFlightDetailsPage = null;
    BaseMethods baseMethods = BaseMethods.getBaseMethods();
    ReviewFlightDetailsPage()
    {
        super(driver);
    }
    public static ReviewFlightDetailsPage getReviewFlightDetial()
    {
        if (reviewFlightDetailsPage == null)
        {
            // To make thread safe
            synchronized (ReviewFlightDetailsPage.class)
            {
                // check again as multiple threads
                // can reach above step
                if (reviewFlightDetailsPage == null)
                    reviewFlightDetailsPage = new ReviewFlightDetailsPage();
            }
        }
        return reviewFlightDetailsPage;
    }
    @FindBy (xpath = "//span[@class='slider round']") public WebElement TravelSlider;
    @FindBy (xpath = "//span[text()=\"Email\"]") public WebElement Email;
    @FindBy (xpath = "//span[text()=\"Phone\"]") public WebElement ContactNo;
    @FindBy (xpath = "//*[@id=\"title_ADT1\"]") public WebElement Adult1Title;
    @FindBy (xpath = "//*[@id=\"title_ADT1\"]/option[2]") public WebElement Adult1TitleMr;
    @FindBy (xpath = "(//span[text()=\"First Name\"])[1]") public WebElement Adult1FirstName;
    @FindBy (xpath = "(//span[text()=\"Last Name\"])[1]") public WebElement Adult1LastName;
    @FindBy (xpath = "//*[@id=\"title_ADT2\"]") public WebElement Adult2Title;
    @FindBy (xpath = "//*[@id=\"title_ADT2\"]/option[3]") public WebElement Adult2TitleMrs;

    @FindBy (xpath = "(//span[text()=\"First Name\"])[2]") public WebElement Adult2FirstName;
    @FindBy (xpath = "(//span[text()=\"Last Name\"])[2]") public WebElement Adult2LastName;
    @FindBy (xpath = "//button[@id=\"pay_submit\"]/b/text()") public WebElement ProceedToPayment;
    @FindBy (xpath = "(//div[text()=\" Price Details\"])[2]") public WebElement Pricedetails;
    @FindBy (xpath = "//div[@class='h5 mt-0 text-light']") public WebElement ExpectedflightLabel;
    @FindBy (xpath = "(//div[text()=' Price Details'])[2]") public WebElement FinalPay;
    public void EnableTravelInsurance()
    {
        baseMethods.waitForElementToBeDisplayed(Pricedetails);
        baseMethods.jsClick(TravelSlider);
    }
    public void InputContactDetails()
    {
        baseMethods.waitForElementAndClick(Email);
        baseMethods.sendValue(Email,"Test@gmail.com");
        baseMethods.waitForElementAndClick(ContactNo);
        baseMethods.sendValue(ContactNo,"9870000000");
    }
    public void  InputTravellerDetails()
    {
        //Adult 1
        baseMethods.waitForElementAndClick(Adult1Title);
        baseMethods.waitForElementAndClick(Adult1TitleMr);
        baseMethods.waitForElementAndClick(Adult1FirstName);
        baseMethods.sendValue(Adult1FirstName,"Test1");
        baseMethods.waitForElementAndClick(Adult1LastName);
        baseMethods.sendValue(Adult1LastName,"Testing1");
        //Adult1 birthdate pending

        //Adult 2
        baseMethods.waitForElementAndClick(Adult2Title);
        baseMethods.waitForElementAndClick(Adult2TitleMrs);
        baseMethods.waitForElementAndClick(Adult2FirstName);
        baseMethods.sendValue(Adult2FirstName,"Test2");
        baseMethods.waitForElementAndClick(Adult2LastName);
        baseMethods.sendValue(Adult2LastName,"Testing2");
        //Adult 2 birthdate pending

    }
    public void ClickOnProceedToPayment() {
        baseMethods.waitForElementAndClick(ProceedToPayment);
    }
    public static String getReviewPageTitle(){
        return driver.getTitle();
    }
    public  String  GetTextYouPay()
    {
        return FinalPay.getText();
    }

}
