package com.yakushkin.framework;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.yakushkin.util.PropertiesReader.getProperty;

public class DriverManager {

    public static void initDriver(String browser) {
        Configuration.browser = browser;
        Configuration.pageLoadTimeout = 20000;
        if (Boolean.parseBoolean(getProperty("test.remote.mode"))) {
            Configuration.remote = getProperty("test.remote.url");
        }
        open();
        getWebDriver().manage().window().maximize();
    }

    public static void initApplicationDefaultDriver() {
        Configuration.browser = DriverProvider.class.getName();
        Configuration.pageLoadTimeout = 20000;
        open();
        getWebDriver().manage().window().maximize();
    }
}
