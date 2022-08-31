package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

public class Screenshots {
    public static void makeScreenshot(WebDriver driver, String fileName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {

            FileUtils.copyFile(scrFile, new File(String.format("src/test/resources/screenshots/%s.png", new Timestamp(System.currentTimeMillis()))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
