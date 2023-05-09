package com.yakushkin.framework;

import com.codeborne.selenide.Browsers;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Arrays;

public enum Driver {

    EDGE(Browsers.EDGE, new EdgeDriverCreator()),
    CHROME(Browsers.CHROME, new ChromeDriverCreator()),
    FIREFOX(Browsers.FIREFOX, new FirefoxDriverCreator());

    private final String driverName;
    private final WebDriverCreator<RemoteWebDriver> webDriverCreator;

    Driver(String driverName, WebDriverCreator<RemoteWebDriver> webDriverCreator) {
        this.driverName = driverName;
        this.webDriverCreator = webDriverCreator;
    }

    public static Driver getWebDriverByName(String driverName) {
        return Arrays.stream(Driver.values())
                .filter(driver -> driver.driverName.equals(driverName))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Driver '" + driverName + "' is not specified in Driver enum"));
    }

    public String getDriverName() {
        return driverName;
    }

    public WebDriverCreator<RemoteWebDriver> getWebDriverCreator() {
        return webDriverCreator;
    }
}
