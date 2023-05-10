package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.SoftAsserts;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
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
    @Severity(SeverityLevel.BLOCKER)
    void loginFromMainPage() {
        Configuration.assertionMode = AssertionMode.SOFT;
        mainPage
                .open()
                .clickOnEnterButton()
                .typingCredentials("***", "***") // type e-mail and password instead ***
                .clickOnLoginButton()
                .clickOnRecaptchaCheckbox();

        mainPage
                .verifyNavigationLogo();
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    void loginFromCatalogPage() {
        Configuration.assertionMode = AssertionMode.SOFT;
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
                .verifyNavigationLogo();
    }
}
