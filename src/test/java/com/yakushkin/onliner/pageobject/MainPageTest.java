package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.Browsers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.yakushkin.framework.DriverManager.initDriver;

public class MainPageTest {

    private MainPage mainPage;

    @BeforeClass
    void init() {
        initDriver(Browsers.CHROME);
        mainPage = new MainPage();
    }

    @Test
    public void checkOpenMainPage() {
        mainPage.open();
    }

    @Test
    public void goToCatalogSection() {
        mainPage
                .open()
                .goToCatalogSection();
    }
}