package ru.netology.qa;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.qa.screens.MainScreen;

import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

@TestInstance(Lifecycle.PER_CLASS)
public class AppTest {

    private AppiumDriver driver;

    @BeforeAll
    public void createDriver() throws MalformedURLException {
        String platform = "android";
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        if ("android".equals(platform)) {
            desiredCapabilities.setCapability(PLATFORM_NAME, "android");
            desiredCapabilities.setCapability(DEVICE_NAME, "any name");
            desiredCapabilities.setCapability(APP_PACKAGE, "ru.netology.testing.uiautomator");
            desiredCapabilities.setCapability(APP_ACTIVITY, "ru.netology.testing.uiautomator.MainActivity");
        } else if ("ios".equals(platform)) {
            desiredCapabilities.setCapability(PLATFORM_NAME, "iOS");
            desiredCapabilities.setCapability(DEVICE_NAME, "iPhone 11");
            desiredCapabilities.setCapability(APP_PACKAGE, "ru.netology.testing.uiautomator");
            desiredCapabilities.setCapability(APP_ACTIVITY, "ru.netology.testing.uiautomator.MainActivity");
        } else {
            throw new IllegalArgumentException(String.format("Platform %s no supported", platform));
        }
        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
    }

    @Test
    public void testSetEmptyRow() {
        MainScreen mainScreen = new MainScreen(driver);
        String initialText = mainScreen.textToBeChanged.getText();

        mainScreen.userInput.click();
        mainScreen.userInput.clear();
        mainScreen.buttonChange.click();

        String actualResult = mainScreen.textToBeChanged.getText();

        Assertions.assertEquals(initialText, actualResult);
    }

    @Test
    public void testOpeningTextInNewActivity() {
        MainScreen mainScreen = new MainScreen(driver);

        String text = "Test";

        mainScreen.userInput.click();
        mainScreen.userInput.setValue(text);
        mainScreen.buttonActivity.click();

        String actualResult = mainScreen.newActivityText.getText();
        Assertions.assertEquals(text, actualResult);
    }

    @AfterAll
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
