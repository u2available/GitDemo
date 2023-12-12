package Core.PropertiesPojo;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;

public class EnvironmentDetail {
    @JsonProperty("environment")
    private String environment;
    @JsonProperty("url")
    private String url;
    @JsonProperty("logPath")
    private String logPath;

    public String getEnvironment() {
        return this.environment; }
    public void setEnvironment(String environment) {
        this.environment = environment; }


    public String getUrl() {
        return this.url; }
    public void setUrl(String url) {
        this.url = url; }


    public String getLogPath() {
        return this.logPath; }
    public void setLogPath(String logPath) {
        this.logPath = logPath; }

}
