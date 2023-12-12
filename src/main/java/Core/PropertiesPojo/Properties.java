package Core.PropertiesPojo;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import java.util.ArrayList;


public class Properties {
    @JsonProperty("AUT")
    private String aUT;
    @JsonProperty("ExecutionEnvironment")
    private String executionEnvironment;
    @JsonProperty("Browser")
    private String browser;
    @JsonProperty("PageLoadTimeout")
    private int pageLoadTimeout;
    @JsonProperty("SeleniumCommandTimeout")
    private int seleniumCommandTimeout;
    @JsonProperty("EnvironmentDetails")
    private ArrayList<EnvironmentDetail> environmentDetails;

    public String getAUT() {
        return this.aUT;
    }

    public void setAUT(String aUT) {
        this.aUT = aUT;
    }


    public String getExecutionEnvironment() {
        return this.executionEnvironment;
    }

    public void setExecutionEnvironment(String executionEnvironment) {
        this.executionEnvironment = executionEnvironment;
    }


    public String getBrowser() {
        return this.browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }


    public int getPageLoadTimeout() {
        return this.pageLoadTimeout;
    }

    public void setPageLoadTimeout(int pageLoadTimeout) {
        this.pageLoadTimeout = pageLoadTimeout;
    }


    public int getSeleniumCommandTimeout() {
        return this.seleniumCommandTimeout;
    }

    public void setSeleniumCommandTimeout(int seleniumCommandTimeout) {
        this.seleniumCommandTimeout = seleniumCommandTimeout;
    }


    public ArrayList<EnvironmentDetail> getEnvironmentDetails() {
        return this.environmentDetails;
    }

    public void setEnvironmentDetails(ArrayList<EnvironmentDetail> environmentDetails) {
        this.environmentDetails = environmentDetails;
    }
}



