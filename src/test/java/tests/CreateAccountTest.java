package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import pages.PersonalInfoPage;
import utils.WebDriverSingleton;

public class CreateAccountTest {
    private static WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = WebDriverSingleton.getDriver();
    }

    @AfterEach
    void cleanup() {
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
        driver.get(TestsConstants.URL);
    }
}
