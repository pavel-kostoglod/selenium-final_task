package pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utils.JSONFileParser;
import utils.WebDriverSingleton;

public class LoginPage {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "email_create")
    WebElement emailInput;

    @FindBy(how = How.ID, using = "email")
    WebElement emailRegisteredInput;

    @FindBy(how = How.ID, using = "passwd")
    WebElement passwordInput;

    @FindBy(how = How.ID, using = "SubmitCreate")
    WebElement createAccountButton;

    @FindBy(how = How.ID, using = "SubmitLogin")
    WebElement signInButton;

    @FindBy(how = How.XPATH, using = "//h1[contains(text(), 'My account')]")
    WebElement myAccountHeader;

    public LoginPage() {
        driver = WebDriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void enterEmail() {
        emailInput.sendKeys(RandomStringUtils.randomAlphabetic(10) + "@mail.test");
    }

    public void enterRegisteredEmail() {
        emailRegisteredInput.sendKeys((String) JSONFileParser.getJsonObjectFromFile().get("USER_EMAIL"));
    }

    public void enterPassword() {
        passwordInput.sendKeys((String) JSONFileParser.getJsonObjectFromFile().get("USER_PASSWORD"));
    }

    public PersonalInfoPage clickCreateAccountButton() {
        createAccountButton.click();
        return PageFactory.initElements(driver, PersonalInfoPage.class);
    }

    public MyAccountPage clickSignInButton() {
        signInButton.click();
        return PageFactory.initElements(driver, MyAccountPage.class);
    }

    public boolean isMyAccountPageDisplayed() {
        return myAccountHeader.isDisplayed();
    }
}
