package Utils;

import Core.Driver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @Author Gladson Antony
 * @Date 14-FEB-2023
 */

public class AllureListener extends Driver implements ITestListener, ISuiteListener, IInvokedMethodListener
{

    /*This belongs to ISuiteListener and will execute before the Suite Starts*/
    @Override
    public void onStart(ISuite arg0)
    {
        Reporter.log("Execution of the Suite '" + arg0.getName() + "' Started!", false);
    }


    /*This belongs to ISuiteListener and will execute after the Suite Ends*/
    @Override
    public void onFinish(ISuite arg0)
    {
        Reporter.log("Execution of the Suite '" + arg0.getName() + "' Completed!", false);
    }


    /*This belongs to ITestListener, It will execute at the time of Test Execution */
    @Override
    public void onStart(ITestContext arg0)
    {
        Reporter.log("About to begin executing Test " + arg0.getName(), false);
    }


    /*This belongs to ITestListener, It will execute at the End of Test*/
    @Override
    public void onFinish(ITestContext arg0)
    {
        Reporter.log("Completed executing test " + arg0.getName(), false);
    }


    /*This belongs to ITestListener, It will Execute only when the Test is PASSED*/
    @Override
    public void onTestSuccess(ITestResult result)
    {
        Reporter.log("\nSUCCESSFULLY EXECUTED TEST: " + result.getTestClass().getName() + "." + result.getMethod().getMethodName() + "\n", true);Reporter.log("\n");
    }


    @Attachment(value = "Entire-page Screenshot of {0}", type = "image/png")
    public static byte[] saveFullPageScreenshot(String name, WebDriver driver)
    {
        try {
            BufferedImage image  = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver).getImage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "Unable to Get Screenshot.".getBytes();
    }

    /*This belongs to ITestListener, It will Execute only when the Test is FAILED*/
    @Override
    public void onTestFailure(ITestResult result)
    {
        String Name = result.getTestClass().getName() + "." + result.getMethod().getMethodName() ;
        Reporter.log("\nFAILED TEST: " + result.getTestClass().getName() + "." + result.getMethod().getMethodName() + "\n", true);
        saveFullPageScreenshot(Name, driver);
    }


    /*This belongs to ITestListener, It and will execute before the Main Test Starts (@Test)*/
    @Override
    public void onTestStart(ITestResult result)
    {
        Reporter.log("\nSTARTED TESTING: " + result.getTestClass().getName() + "." + result.getMethod().getMethodName() + "\n", true);
    }


    /*This belongs to ITestListener, It will execute only if any of the Main Test(@Test) Gets Skipped*/
    @Override
    public void onTestSkipped(ITestResult result)
    {
        Reporter.log("\nSKIPPED TEST: " + result.getTestClass().getName() + "." + result.getMethod().getMethodName() + "\n", true);
    }


    /*This belongs to IInvokedMethodListener, It will execute before every method Including @Before @After @Test*/
    public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1)
    {
        String textMsg = "About to begin executing following method : " + returnMethodName(arg0.getTestMethod());
        Reporter.log(textMsg, false);
    }


    /*This belongs to IInvokedMethodListener and will execute after every method Including @Before @After @Test*/
    public void afterInvocation(IInvokedMethod arg0, ITestResult arg1)
    {
        String textMsg = "Completed executing following method : " + returnMethodName(arg0.getTestMethod());
        Reporter.log(textMsg, false);
    }


    /*This will return method names to the calling function*/
    private String returnMethodName(ITestNGMethod method)
    {
        return method.getRealClass().getSimpleName() + "." + method.getMethodName();
    }


    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result)
    { }

}