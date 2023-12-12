package Utils;

import Pages.HomePage;
import org.openqa.selenium.WebElement;

import static Core.Driver.getDefaultDriver;

public class NavigationMethods {

    private String old_win;
    private WebElement element;
    private String lastWinHandle;
    private  static NavigationMethods navigationMethods=null;

    private NavigationMethods() {
        old_win=null;
        element=null;
        lastWinHandle=null;
    }

    public static NavigationMethods getNavigationMethods() {
        if (navigationMethods == null)
        {
            // To make thread safe
            synchronized (NavigationMethods.class)
            {
                // check again as multiple threads
                // can reach above step
                if (navigationMethods==null)
                    navigationMethods = new NavigationMethods();
            }
        }
        return navigationMethods;
    }

    /** Method to open link
     * @param url : String : URL for navigation
     */
    public void navigateTo(String url)
    {
        getDefaultDriver().get(url);
    }
}
