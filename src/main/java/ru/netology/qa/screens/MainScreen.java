package ru.netology.qa.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import java.net.URL;

import java.time.Duration;
import java.util.List;

public class MainScreen {

    @AndroidFindBy(id = "userInput")
    @iOSXCUITFindBy(id = "userInput")
    public MobileElement userInput;

    @AndroidFindBy(id = "buttonChange")
    @iOSXCUITFindBy(id = "buttonChange")
    public MobileElement buttonChange;

    @AndroidFindBy(id = "buttonActivity")
    @iOSXCUITFindBy(id = "buttonActivity")
    public MobileElement buttonActivity;

    @AndroidFindBy(id = "textToBeChanged")
    @iOSXCUITFindBy(id = "textToBeChanged")
    public MobileElement textToBeChanged;

    @AndroidFindBy(id = "text")
    @iOSXCUITFindBy(id = "text")
    public MobileElement newActivityText;

    private AppiumDriver driver;

    public MainScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(15)), this);
    }
}


