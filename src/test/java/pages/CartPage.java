package pages;

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

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(how = How.ID, using = "summary_products_quantity")
    WebElement numberOfProducts;
    @FindBy(how = How.XPATH, using = "//tr[contains(@class, 'first_item')]//a[@title='Delete']")
    WebElement firstProductDeleteButton;

    public CartPage() {
        driver = WebDriverSingleton.getDriver();
        wait = WebDriverWaitSingleton.getWaiter();
        PageFactory.initElements(driver, this);
    }

    public char getNumberOfProducts() {
        return numberOfProducts.getText().charAt(0);
    }

    public void deleteAllProducts() {
        while (driver.findElements(By.xpath("//tr[contains(@class, 'cart_item')]")).size() != 0) {
            deleteFirstProduct();
        }
    }

    private void deleteFirstProduct() {
        int numberOfProductRows = driver.findElements(By.xpath("//tr[contains(@class, 'cart_item')]")).size();
        System.out.println(numberOfProductRows);
        firstProductDeleteButton.click();
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(
                By.xpath("//tr[contains(@class, 'cart_item')]"), numberOfProductRows));
    }

    public boolean isAnyProductInTheCart() {
        try {
            driver.findElement(By.xpath("//tr[contains(@class, 'cart_item')]"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
