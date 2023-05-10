package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static java.time.Duration.ofSeconds;

public class LoginForm {

    public LoginForm() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    public LoginForm typingCredentials(String userNameOrEmail, String password) {
        $x("//input[contains(@placeholder,'Ник или e-mail')]")
                .shouldBe(visible, ofSeconds(5))
                .setValue(userNameOrEmail);
        $x("//input[contains(@placeholder,'Пароль')]")
                .shouldBe(visible, ofSeconds(5))
                .setValue(password);

        return this;
    }

    public LoginForm clickOnLoginButton() {
        $x("//button[contains(text(),'Войти')]")
                .shouldBe(and("clickable", exist, visible), ofSeconds(5))
                .click();

        return this;
    }

    public void clickOnRecaptchaCheckbox() {
        try {
            switchTo().frame($x("//iframe[@title='reCAPTCHA']"));
            $x("//label[contains(text(),'Я не робот')]")
                    .shouldBe(and("clickable", exist, visible), ofSeconds(5))
                    .click();
        } finally {
            switchTo().defaultContent();
        }
    }

    public void clickOnCloseCross() {
        $x("//div[@class='auth-form__close']")
                .shouldBe(and("clickable", exist, visible), ofSeconds(5))
                .click();
    }
}
