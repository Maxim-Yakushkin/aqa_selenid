package com.yakushkin.framework;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.open;

public class DriverManager {

    public static void initDriver(String browser) {
        Configuration.browser = browser;
        Configuration.pageLoadTimeout = 20000;
        open();
    }
}
