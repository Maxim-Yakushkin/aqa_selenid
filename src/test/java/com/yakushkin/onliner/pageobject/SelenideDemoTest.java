package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.yakushkin.enumiration.OnlinerBaseUrl.MAIN_PAGE_URL;

public class SelenideDemoTest {

    @Test
    void demoTest() throws InterruptedException {
        Configuration.browser = Browsers.FIREFOX;
        open(MAIN_PAGE_URL.getUrl());
        var enterButton = $x("//div[contains(text(),'Вход')]");
        enterButton.click();
        SelenideElement userNameInput = $x("//input[contains(@placeholder,'Ник или e-mail')]");
        SelenideElement passwordInput = $x("//input[contains(@placeholder,'Пароль')]");
        SelenideElement loginButton = $x("//button[contains(text(),'Войти')]");
        userNameInput.setValue("max-yakushkin1@yandex.ru");
        passwordInput.setValue("maxior3320");
        loginButton.click();
        switchTo().frame($x("//iframe[@title='reCAPTCHA']"));
        SelenideElement recaptchaCheckbox = $x("//label[contains(text(),'Я не робот')]");
        recaptchaCheckbox.click();
        switchTo().defaultContent();

        Thread.sleep(5000);
    }
}
