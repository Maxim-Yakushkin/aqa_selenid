package com.yakushkin.onliner.pageobject;

import com.yakushkin.onliner.pageobject.MainPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MainPageTest {

    private MainPage mainPage;

    @BeforeClass
    void init() {
        mainPage = new MainPage();
    }

    @Test
    public void openMainPage() {
        mainPage.open();
    }

    @Test
    public void goToCatalogSection() {
        mainPage
                .open()
                .goToCatalogSection();
    }
}