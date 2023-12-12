import Core.BaseSingletonMap;
import Core.Driver;
import Pages.HomePage;
import Pages.SearchResultsPage;

import java.text.ParseException;
import java.time.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

public class FirstTestSample extends BaseSingletonMap {

    @Test
    public void FindFlight() throws InterruptedException, ParseException {
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
    public void  SortSearchResultsAndSelect() throws ParseException, InterruptedException {
        FindFlight();
        SearchResultsPage.getSearchPage().ClickOnSelect();
        Assert.assertEquals(reviewFlightDetailsPage.getReviewPageTitle(),"Review & Book Flight New Delhi to Mumbai - TripOdeal.com");
    }
    @Test
    public void ReviewFlightDetails() throws ParseException, InterruptedException {
        SortSearchResultsAndSelect();
        reviewFlightDetailsPage.EnableTravelInsurance();
        Assert.assertEquals(reviewFlightDetailsPage.GetTextYouPay(),"Price Details");
    }
}
