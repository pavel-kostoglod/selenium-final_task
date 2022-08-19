package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utils.WebDriverSingleton;

public class HomePage {
    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//*[@title='Log in to your customer account']")
    WebElement signInButton;

    public HomePage() {
        driver = WebDriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    public LoginPage clickSignInButton() {
        signInButton.click();
        return PageFactory.initElements(driver, LoginPage.class);
    }
}
