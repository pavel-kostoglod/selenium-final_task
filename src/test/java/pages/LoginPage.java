package pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utils.WebDriverSingleton;

public class LoginPage {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "email_create")
    WebElement emailInput;

    @FindBy(how = How.ID, using = "SubmitCreate")
    WebElement createAccountButton;

    public LoginPage() {
//        wait = WebDriverWaitSingleton.getWaiter();
        driver = WebDriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void enterEmail() {
        emailInput.sendKeys(RandomStringUtils.randomAlphabetic(10) + "@mail.test");
    }

    public PersonalInfoPage clickCreateAccountButton() {
        createAccountButton.click();
        return PageFactory.initElements(driver, PersonalInfoPage.class);
    }
}
