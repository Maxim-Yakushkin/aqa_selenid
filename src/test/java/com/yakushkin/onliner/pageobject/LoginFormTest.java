package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.SoftAsserts;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({SoftAsserts.class})
public class LoginFormTest {

    private MainPage mainPage;
    private CatalogPage catalogPage;

    @BeforeClass
    void init() {
        Configuration.assertionMode = AssertionMode.STRICT;
        mainPage = new MainPage();
        catalogPage = new CatalogPage();
    }

    @Test
    void loginFromMainPage() {
        Configuration.assertionMode = AssertionMode.SOFT;
        mainPage
                .open()
                .clickOnEnterButton()
                .typingCredentials("max-yakushkin1@yandex.ru", "MaxiOr3320!&@") // type e-mail and password instead ***
                .clickOnLoginButton()
                .clickOnRecaptchaCheckbox();

        mainPage
                .verifyNavigationLogo();
    }

    @Test
    void loginFromCatalogPage() {
        Configuration.assertionMode = AssertionMode.SOFT;
        catalogPage
                .open()
                .clickOnEnterButton()
                .typingCredentials("max-yakushkin1@yandex.ru", "MaxiOr3320!&@") // type e-mail and password instead ***
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
                .verifyNavigationLogo();
    }
}
