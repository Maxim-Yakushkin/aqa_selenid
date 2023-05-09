package com.yakushkin.framework;

import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverCreatorFactory {

    public static WebDriverCreator<RemoteWebDriver> getDriverCreator(String browserName) {
        return Driver.getWebDriverByName(browserName).getWebDriverCreator();
    }
}
