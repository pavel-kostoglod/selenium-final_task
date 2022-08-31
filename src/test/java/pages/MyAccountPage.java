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

public class MyAccountPage {
    private WebDriver driver;
    private static WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//*[@title='My wishlists']")
    WebElement myWishlistButton;

    public MyAccountPage() {
        driver = WebDriverSingleton.getDriver();
        wait = WebDriverWaitSingleton.getWaiter();
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOf(myWishlistButton));
    }

    public MyWishlistPage clickMyWishlistButton() {
        myWishlistButton.click();
        return PageFactory.initElements(driver, MyWishlistPage.class);
    }
}
