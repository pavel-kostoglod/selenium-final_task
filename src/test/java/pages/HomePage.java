package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverSingleton;
import utils.WebDriverWaitSingleton;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//*[@title='Log in to your customer account']")
    WebElement signInButton;
    @FindBy(how = How.XPATH, using = "//a[@title='View my customer account']/parent::div")
    WebElement viewMyAccountButton;
    @FindBy(how = How.XPATH, using = "//*[@id='layer_cart']//*[@title='Close window']")
    WebElement closeCartLayer;
    @FindBy(how = How.XPATH, using = "//*[@title='View my shopping cart']")
    WebElement cartButton;
    @FindBy(how = How.ID, using = "layer_cart")
    WebElement cartLayer;
    @FindBy(how = How.ID, using = "search_query_top")
    WebElement searchInput;

    public HomePage() {
        driver = WebDriverSingleton.getDriver();
        wait = WebDriverWaitSingleton.getWaiter();
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOf(searchInput));
    }

    public LoginPage clickSignInButton() {
        signInButton.click();
        return PageFactory.initElements(driver, LoginPage.class);
    }

    public MyAccountPage clickViewMyAccountButton() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        viewMyAccountButton.click();
        return PageFactory.initElements(driver, MyAccountPage.class);
    }

    public void addRandomProductToCart() {
        int random = new Random().nextInt(6) + 1;
        String randomProductTileElement = String.format(
                "(//*[@id='homefeatured']/li)[%d]", random
        );
        String randomProductAddToCartButton = String.format(
                "(//*[@id='homefeatured']/li)[%d]//*[@title='Add to cart']", random
        );
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath(randomProductTileElement))).perform();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath(randomProductAddToCartButton)).click();
        wait.until(ExpectedConditions.visibilityOf(cartLayer));
        closeCartLayer.click();
    }

    public CartPage goToCart() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        cartButton.click();
        return PageFactory.initElements(driver, CartPage.class);
    }
}
