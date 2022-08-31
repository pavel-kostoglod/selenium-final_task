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

import java.util.Objects;
import java.util.Random;

public class MyWishlistPage {
    private WebDriver driver;
    private static WebDriverWait wait;
    private String randomProductXPath = String.format(
            "(//a[@class='product-name'])[%d]", new Random().nextInt(8) + 1
    );

    @FindBy(how = How.XPATH, using = "//h1[contains(text(), 'My wishlists')]")
    WebElement myWishlistsHeader;
    @FindBy(how = How.XPATH, using = "//h1[contains(text(), 'My wishlists')]")
    WebElement myWishlistRecord;
    @FindBy(how = How.XPATH, using = "(//*[@id='block-history']//td)[2]")
    WebElement productsNumberInWishlist;
    @FindBy(how = How.XPATH, using = "//td[@class='wishlist_delete']/a")
    WebElement deleteMyWishlistButton;
    @FindBy(how = How.ID, using = "name")
    WebElement wishlistNameInput;
    @FindBy(how = How.ID, using = "submitWishlist")
    WebElement createWishlistButton;

    public MyWishlistPage() {
        driver = WebDriverSingleton.getDriver();
        wait = WebDriverWaitSingleton.getWaiter();
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOf(myWishlistsHeader));
    }

    public ProductPage goToRandomProductPage() {
        driver.findElement(By.xpath(randomProductXPath)).click();
        return PageFactory.initElements(driver, ProductPage.class);
    }

    public boolean isAnyWishlistCreated() {
        try {
            driver.findElement(By.xpath("//*[@id='block-history']"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isProductAddedToWishlist() {
        return !Objects.equals(productsNumberInWishlist.getText(), "0");
    }

    public void deleteMyWishlist() {
        deleteMyWishlistButton.click();
        driver.switchTo().alert().accept();
    }

    public void createCustomWishlist() {
        wishlistNameInput.sendKeys(RandomStringUtils.randomAlphabetic(8));
        createWishlistButton.click();
    }
}
