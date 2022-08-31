package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.JSONFileParser;
import utils.TestResultsWatcher;
import utils.WebDriverSingleton;

@ExtendWith(TestResultsWatcher.class)
public class AddProductToAutoCreatedWishlistTest {
    private static WebDriver driver;

    @BeforeAll
    static void setup() {
        driver = WebDriverSingleton.getDriver();
    }

    @AfterAll
    static void cleanup() {
        WebDriverSingleton.quitDriver();
    }

    @AfterEach
    void testCleanup() {
        loadHomePage();
        HomePage homePage = new HomePage();
        MyAccountPage myAccountPage = homePage.clickViewMyAccountButton();
        MyWishlistPage myWishlistPage = myAccountPage.clickMyWishlistButton();
        myWishlistPage.deleteMyWishlist();
    }

    @Test
    void verifyLoginWithExistentAccount () {
        loadHomePage();
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickSignInButton();
        loginPage.enterRegisteredEmail();
        loginPage.enterPassword();
        MyAccountPage myAccountPage = loginPage.clickSignInButton();
        MyWishlistPage myWishlistPage = myAccountPage.clickMyWishlistButton();
        Assertions.assertFalse(myWishlistPage.isAnyWishlistCreated());

        ProductPage productPage = myWishlistPage.goToRandomProductPage();
        productPage.addProductToWishlist();
        myAccountPage = productPage.clickViewMyAccountButton();
        myWishlistPage = myAccountPage.clickMyWishlistButton();
        Assertions.assertTrue(myWishlistPage.isAnyWishlistCreated());
        Assertions.assertTrue(myWishlistPage.isProductAddedToWishlist());
    }

    private static void loadHomePage() {
        driver.get((String) JSONFileParser.getJsonObjectFromFile().get("URL"));
    }
}
