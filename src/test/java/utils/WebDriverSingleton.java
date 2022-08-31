package utils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverSingleton {

    private static WebDriver driver;
    private static String launch_option = (String) JSONFileParser.getJsonObjectFromFile().get("LAUNCH_OPTION");

    private WebDriverSingleton() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            if (launch_option.equals("LOCALLY")) {
                System.setProperty("webdriver.chrome.driver",
                        (String) JSONFileParser.getJsonObjectFromFile().get("CHROME_DRIVER_PATH"));
                driver = new ChromeDriver();
//
            } else if (launch_option.equals("SELENIUM_GRID")) {
                try {
                driver = new RemoteWebDriver(
                        new URL((String) JSONFileParser.getJsonObjectFromFile().get("SELENIUM_GRID_URL")),
                                DesiredCapabilities.chrome()
                );
            } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {driver.quit();}
        driver=null;
    }
}
