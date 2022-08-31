package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverSingleton;
import utils.WebDriverWaitSingleton;

import java.util.concurrent.TimeUnit;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(how = How.ID, using = "wishlist_button")
    WebElement addToWishlistButton;
    @FindBy(how = How.XPATH, using = "//a[@title='Close']")
    WebElement closePopupButton;
    @FindBy(how = How.XPATH, using = "//a[@title='View my customer account']/parent::div")
    WebElement viewMyAccountButton;

    public ProductPage() {
        driver = WebDriverSingleton.getDriver();
        wait = WebDriverWaitSingleton.getWaiter();
        PageFactory.initElements(driver, this);
    }

    public void addProductToWishlist() {
        addToWishlistButton.click();
        wait.until(ExpectedConditions.visibilityOf(closePopupButton));
        closePopupButton.click();
    }

    public MyAccountPage clickViewMyAccountButton() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        viewMyAccountButton.click();
        return PageFactory.initElements(driver, MyAccountPage.class);
    }
}
