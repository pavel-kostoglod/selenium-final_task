package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import utils.JSONFileParser;
import utils.TestResultsWatcher;
import utils.WebDriverSingleton;


@ExtendWith(TestResultsWatcher.class)
public class LoginTest {
    private static WebDriver driver;

    @BeforeAll
    static void setup() {
        driver = WebDriverSingleton.getDriver();
    }

    @AfterAll
    static void cleanup() {
        WebDriverSingleton.quitDriver();
    }

    @Test
    void verifyLoginWithExistentAccount () {
        loadHomePage();
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickSignInButton();
        loginPage.enterRegisteredEmail();
        loginPage.enterPassword();
        loginPage.clickSignInButton();

        Assertions.assertTrue(loginPage.isMyAccountPageDisplayed());
    }

    private void loadHomePage() {
        driver.get((String) JSONFileParser.getJsonObjectFromFile().get("URL"));
    }
}