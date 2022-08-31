package utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Optional;

public class TestResultsWatcher implements TestWatcher {
    private final Capabilities capabilities;

    public TestResultsWatcher() {
        String fileName = RandomStringUtils.randomAlphabetic(10);
        WebDriver driver = WebDriverSingleton.getDriver();
        this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Screenshots.makeScreenshot(WebDriverSingleton.getDriver(), RandomStringUtils.randomAlphabetic(10));
        System.out.println(capabilities.getBrowserName());
        WebDriverSingleton.quitDriver();
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        WebDriverSingleton.quitDriver();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        WebDriverSingleton.quitDriver();
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        WebDriverSingleton.quitDriver();
    }
}