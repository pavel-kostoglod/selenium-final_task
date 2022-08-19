package pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverSingleton;
import utils.WebDriverWaitSingleton;

import java.util.Random;

public class PersonalInfoPage {
    private WebDriver driver;
    private static WebDriverWait wait;
    private String randomStateXPath = String.format("//*[@id='id_state']/option[@value='%d']", new Random().nextInt(50) + 1);

    @FindBy(how = How.ID, using = "customer_firstname")
    WebElement firstNameInput;
    @FindBy(how = How.ID, using = "customer_lastname")
    WebElement lastNameInput;
    @FindBy(how = How.ID, using = "passwd")
    WebElement passwordInput;
    @FindBy(how = How.ID, using = "address1")
    WebElement addressInput;
    @FindBy(how = How.ID, using = "city")
    WebElement cityInput;
    @FindBy(how = How.ID, using = "id_state")
    WebElement stateSelect;
    // only constants should be used in the annotations
//    @FindBy(how = How.XPATH, using = String.format("//*[@id='id_state']/option[@value='%d']", new Random().nextInt(50) + 1))
//    WebElement randomStateOption;
    @FindBy(how = How.ID, using = "postcode")
    WebElement postcodeInput;
    @FindBy(how = How.ID, using = "phone_mobile")
    WebElement mobilePhoneInput;
    @FindBy(how = How.ID, using = "submitAccount")
    WebElement submitButton;
    @FindBy(how = How.XPATH, using = "//h1[contains(text(), 'My account')]")
    WebElement myAccountHeader;

    public PersonalInfoPage() {
        wait = WebDriverWaitSingleton.getWaiter();
        driver = WebDriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void fillInRequiredFields() {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        firstNameInput.sendKeys(RandomStringUtils.randomAlphabetic(8));
        lastNameInput.sendKeys(RandomStringUtils.randomAlphabetic(8));
        passwordInput.sendKeys(RandomStringUtils.randomAlphabetic(8));
        addressInput.sendKeys(RandomStringUtils.randomAlphabetic(8));
        cityInput.sendKeys(RandomStringUtils.randomAlphabetic(8));
        stateSelect.click();
        driver.findElement(By.xpath(randomStateXPath)).click();
        postcodeInput.sendKeys(RandomStringUtils.randomNumeric(5));
        mobilePhoneInput.sendKeys(RandomStringUtils.randomNumeric(8));
    }

    public MyAccountPage clickSubmitButton() {
        submitButton.click();
        return PageFactory.initElements(driver, MyAccountPage.class);
    }

    public boolean isMyAccountPageDisplayed() {
        return myAccountHeader.isDisplayed();
    }
}
