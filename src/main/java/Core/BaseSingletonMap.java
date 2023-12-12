package Core;

import Pages.HomePage;
import Pages.PaymentPage;
import Pages.ReviewFlightDetailsPage;
import Pages.SearchResultsPage;
import Utils.BaseMethods;
import Utils.NavigationMethods;

/**
 * Extend the base Singleton Map class on test classes to get all the required methods
 */
public class BaseSingletonMap {
    private static volatile BaseSingletonMap baseSingletonMap  = null;
    protected HomePage homePage;
    protected SearchResultsPage searchPage;
    protected ReviewFlightDetailsPage reviewFlightDetailsPage;
    protected NavigationMethods navigationMethods;
    protected BaseMethods baseMethods;
    protected PaymentPage paymentPage;
    protected BaseSingletonMap() {

        //instantiate all get methods of page classes and common libraries through here
        //Example
         this.homePage          = HomePage.getHomePage();
         this.searchPage        = SearchResultsPage.getSearchPage();
         this.reviewFlightDetailsPage = ReviewFlightDetailsPage.getReviewFlightDetial();
         this.paymentPage = PaymentPage.getPaymentPage();
         this.navigationMethods = NavigationMethods.getNavigationMethods();
         this.baseMethods = BaseMethods.getBaseMethods();
    }

    public static BaseSingletonMap getInstance()
    {
        if (baseSingletonMap == null)
        {
            // To make thread safe
            synchronized (BaseSingletonMap.class)
            {
                // check again as multiple threads
                // can reach above step
                if (baseSingletonMap==null)
                    baseSingletonMap = new BaseSingletonMap();
            }
        }
        return baseSingletonMap;
    }
}
