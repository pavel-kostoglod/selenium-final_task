package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.JSONFileParser;
import utils.TestResultsWatcher;
import utils.WebDriverSingleton;

@ExtendWith(TestResultsWatcher.class)
public class AddProductsToCartTest {
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
        CartPage cartPage = homePage.goToCart();
        cartPage.deleteAllProducts();
        Assertions.assertFalse(cartPage.isAnyProductInTheCart());
    }

    @Test
    void verifyAdding3ProductsToCart () {
        loadHomePage();
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickSignInButton();
        loginPage.enterRegisteredEmail();
        loginPage.enterPassword();
        loginPage.clickSignInButton();
        loadHomePage();
        homePage.addRandomProductToCart();
        homePage.addRandomProductToCart();
        homePage.addRandomProductToCart();
        CartPage cartPage = homePage.goToCart();
        Assertions.assertEquals(cartPage.getNumberOfProducts(), '3');
    }

    private static void loadHomePage() {
        driver.get((String) JSONFileParser.getJsonObjectFromFile().get("URL"));
    }
}
