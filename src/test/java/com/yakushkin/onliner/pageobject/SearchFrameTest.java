package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.Browsers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.yakushkin.framework.DriverManager.initDriver;

public class SearchFrameTest {

    private MainPage mainPage;

    @BeforeClass
    void init() {
        initDriver(Browsers.FIREFOX);
        mainPage = new MainPage();
    }

    @Test
    void checkSearchFrameTabs() {
        mainPage
                .open()
                .typingIntoSearchLine("lego")
                .getAllSearchTabItems();
    }

    @Test
    void checkSearchResultCategoriesInCatalogTab() {
        mainPage
                .open()
                .typingIntoSearchLine("lego")
                .getCatalogSearchResultCategories();
    }

    @Test
    void checkSearchResultInNewsTabWithSelectedCheckBoxWithVideo() {
        mainPage
                .open()
                .typingIntoSearchLine("lego")
                .clickOnNews()
                .clickInNewsOnVideoFilterCheckbox()
                .getNewsSearchResultWithVideo();
    }
}
