package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.WebDriverSingleton;

public class MyAccountPage {
    private WebDriver driver;

    public MyAccountPage() {
//        wait = WebDriverWaitSingleton.getWaiter();
        driver = WebDriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }
}
