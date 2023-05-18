package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.testng.SoftAsserts;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.yakushkin.framework.DriverManager.initDriver;

@Listeners({SoftAsserts.class})
public class MainPageTest {

    private MainPage mainPage;

    @BeforeClass
    void init() {
        closeWebDriver();
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
                .clickOnCatalogSection();
    }
}