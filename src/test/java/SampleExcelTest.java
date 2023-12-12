import Core.PropertiesReader;
import Utils.ExcelUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.Tag;
import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

@Tag("Data-provider Test Class")
public class SampleExcelTest {
    ExcelUtils reader = new ExcelUtils(PropertiesReader.testdatapath); //create an object of ExcelUtils class by passing testdatapath as argument from Properties Reader class
    public static String sheet = "register";
    private static final String column_name = "Scenario";
    @Test(dataProvider = "Userformdata")
    @Tag("Data-provider Test Method")
    @Owner("Test Owner")
    @Description("Test to demonstrate the use of TestNG's Data Provider from Excel Spreadsheet")
    @Severity(SeverityLevel.MINOR)
    public void filldata(Map<String, String> data) throws EncryptedDocumentException, IOException
    {
        System.out.println(data);
        System.out.println(data.get("firstname")); //to read the data using header as key
        System.out.println("*********************");
    }

    @DataProvider(name = "Userformdata")
    public Object[][] UserData() throws EncryptedDocumentException, IOException
    {
        Object[][] data = reader.extractRowData(sheet, column_name);
        return data;
    }
}
