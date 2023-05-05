package com.yakushkin.onliner.pageobject;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static java.time.Duration.ofSeconds;

public class LoginPage extends BasePage {

    public LoginPage typingCredentials(String userNameOrEmail, String password) {
        $x("//input[contains(@placeholder,'Ник или e-mail')]")
                .shouldBe(visible, ofSeconds(5))
                .setValue(userNameOrEmail);
        $x("//input[contains(@placeholder,'Пароль')]")
                .shouldBe(visible, ofSeconds(5))
                .setValue(password);

        return this;
    }

    public LoginPage clickOnLoginButton() {
        $x("//button[contains(text(),'Войти')]")
                .shouldBe(exist, visible).click();

        return this;
    }

    public void clickOnRecaptchaCheckbox() {
        switchTo().frame($x("//iframe[@title='reCAPTCHA']"));
        $x("//label[contains(text(),'Я не робот')]")
                .shouldBe(exist, visible)
                .click();
        switchTo().defaultContent();
    }

    public void clickOnCloseCross() {
        $x("//div[@class='auth-form__close']")
                .shouldBe(exist, visible)
                .click();
    }
}
