import Core.BaseSingletonMap;
import Pages.SearchResultsPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.ParseException;

@Epic("Allure Report Sample")
@Feature("Basic Test")
public class Testscript extends BaseSingletonMap
    {
        @Test
        @Description("To Open the URL and Search for Round Trip")
        @Severity(SeverityLevel.NORMAL)
        public void Test() throws InterruptedException, ParseException {
            navigationMethods.navigateTo("https://www.tripodeal.com/");
            //----------------------HomePage-----------------------------
            homePage.ClickRoundTrip();
            homePage.SelectCity();

            homePage.SelectDate();
            homePage.SelectTraveller();
            homePage.SelectCabin();
            homePage.ClickonSearchButton();
        }
        @Test
        public void  SortSearchResultsAndSelect() throws InterruptedException, ParseException {
            Test();
            SearchResultsPage.getSearchPage().ClickOnSelect();
            Assert.assertEquals(reviewFlightDetailsPage.getReviewPageTitle(),"Review & Book Flight New Delhi to Mumbai - TripOdeal.com");
        }
        @Test
        public void ReviewFlightDetails() throws InterruptedException, ParseException {
            SortSearchResultsAndSelect();
            reviewFlightDetailsPage.EnableTravelInsurance();
        }
    }
