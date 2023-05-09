package com.yakushkin.onliner.pageobject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginPageTest {

    private MainPage mainPage;
    private CatalogPage catalogPage;

    @BeforeClass
    void init() {
        mainPage = new MainPage();
        catalogPage = new CatalogPage();
    }

    @Test
    void loginFromMainPage() {
        mainPage
                .open()
                .clickOnEnterButton()
                .typingCredentials("***", "***") // type e-mail and password instead ***
                .clickOnLoginButton()
                .clickOnRecaptchaCheckbox();

        mainPage
                .getNavigationLogo();
    }

    @Test
    void loginFromCatalogPage() {
        catalogPage
                .open()
                .clickOnEnterButton()
                .typingCredentials("***", "***") // type e-mail and password instead ***
                .clickOnLoginButton()
                .clickOnRecaptchaCheckbox();

        catalogPage
                .verifyNavigationTitle();
    }

    @Test
    void checkClosingLoginForm() {
        mainPage
                .open()
                .clickOnEnterButton()
                .clickOnCloseCross();

        mainPage
                .getNavigationLogo();
    }
}
