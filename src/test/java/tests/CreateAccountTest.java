package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.PersonalInfoPage;
import utils.JSONFileParser;
import utils.TestResultsWatcher;
import utils.WebDriverSingleton;


@ExtendWith(TestResultsWatcher.class)
public class CreateAccountTest {
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
    void verifyAccountCreation () {
        loadHomePage();
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickSignInButton();
        loginPage.enterEmail();
        PersonalInfoPage personalInfoPage = loginPage.clickCreateAccountButton();
        personalInfoPage.fillInRequiredFields();
        personalInfoPage.clickSubmitButton();

        Assertions.assertTrue(personalInfoPage.isMyAccountPageDisplayed());
    }

    private void loadHomePage() {
        driver.get((String) JSONFileParser.getJsonObjectFromFile().get("URL"));
    }
}
