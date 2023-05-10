package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.testng.SoftAsserts;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.yakushkin.framework.DriverManager.initDriver;

@Listeners({ SoftAsserts.class})
public class SearchFrameTest {

    private MainPage mainPage;

    @BeforeClass
    void init() {
        closeWebDriver();
        initDriver(Browsers.FIREFOX);
        mainPage = new MainPage();
    }

    @Test
    void checkSearchFrameTabs() {
        mainPage
                .open()
                .typingIntoSearchLine("lego")
                .verifyAllSearchTabItems();
    }

    @Test
    void checkSearchResultCategoriesInCatalogTab() {
        mainPage
                .open()
                .typingIntoSearchLine("lego")
                .verifyCatalogSearchResultCategories();
    }

    @Test
    void checkSearchResultInNewsTabWithSelectedCheckBoxWithVideo() {
        mainPage
                .open()
                .typingIntoSearchLine("lego")
                .clickOnNews()
                .clickInNewsOnVideoFilterCheckbox()
                .verifyNewsSearchResultWithVideo();
    }
}
