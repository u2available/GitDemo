package Pages;
import Core.PageFactoryBase;
import Utils.BaseMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends PageFactoryBase
{
    private static volatile PaymentPage paymentPage = null;
    BaseMethods baseMethods = BaseMethods.getBaseMethods();
    PaymentPage()
    {
        super(driver);
    }
    public static PaymentPage getPaymentPage() {
        if (paymentPage == null) {
            // To make thread safe
            synchronized (PaymentPage.class) {
                // check again as multiple threads
                // can reach above step
                if (paymentPage == null)
                    paymentPage = new PaymentPage();
            }
        }
        return paymentPage;
    }

    @FindBy(xpath = "//div[contains(text(), 'Review Details')]") public WebElement ReviewDetails;
    @FindBy(xpath = "//a[contains(text(),'Pay Now')]") public WebElement PayNowButton;

    public void CheckTravelerdetails()
    {
        ReviewDetails.click();
    }

}
