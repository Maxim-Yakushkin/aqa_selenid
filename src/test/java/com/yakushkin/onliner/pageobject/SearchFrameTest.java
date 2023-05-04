package com.yakushkin.onliner.pageobject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchFrameTest {

    private MainPage mainPage;

    @BeforeClass
    void init() {
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
    void getSearchResultCategoriesInCatalogTab() {
        mainPage
                .open()
                .typingIntoSearchLine("lego")
                .getCatalogSearchResultCategories();
    }

    @Test
    void getSearchResultInNewsTabWithSelectedCheckBoxWithVideo() {
        mainPage
                .open()
                .typingIntoSearchLine("lego")
                .clickOnNews()
                .clickInNewsOnVideoFilterCheckbox()
                .getNewsSearchResultWithVideo();
    }
}
