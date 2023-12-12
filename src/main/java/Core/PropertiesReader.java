package Core;

import Core.PropertiesPojo.EnvironmentDetail;
import Core.PropertiesPojo.Properties;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;

public class PropertiesReader {
    public static String testdatapath = System.getProperty("user.dir")+File.separator+Settings.testDataPath;
    public static void ReadProperties(){

        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            String pathName = System.getProperty("user.dir")+File.separator+"src\\main\\resources\\Properties.json";
            System.out.println("Pathname:" + pathName);
            Properties properties = objectMapper.readValue(new File(pathName), Properties.class);
            Settings.Aut = properties.getAUT();
            Settings.ExecutionEnvironment = properties.getExecutionEnvironment();
            Settings.Browser = properties.getBrowser();
            Settings.PageLoadTimeOut = properties.getPageLoadTimeout();
            Settings.SeleniumCommandTimeOut = properties.getSeleniumCommandTimeout();
            ArrayList<EnvironmentDetail> environmentDetails = properties.getEnvironmentDetails();
            for (int i = 0; i< environmentDetails.size() ; i++){
                String env = environmentDetails.get(i).getEnvironment();
                if (env.equalsIgnoreCase(Settings.ExecutionEnvironment))
                {
                    Settings.Url = environmentDetails.get(i).getUrl();
                    Settings.LogPath = environmentDetails.get(i).getLogPath();
                }

            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
