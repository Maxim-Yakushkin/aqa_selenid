package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.yakushkin.framework.DriverManager;
import lombok.Data;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static java.time.Duration.ofSeconds;

@Data
public abstract class BasePage {

    public BasePage() {
        DriverManager.initDriver(Browsers.FIREFOX);
    }

    public BasePage open() {
        navigateTo("");
        return this;
    }

    public void navigateTo(String url) {
        Selenide.open(url);
    }

    public SearchFrame typingIntoSearchLine(String request) {
        $x("//input[@class='fast-search__input']")
                .shouldBe(visible, ofSeconds(5))
                .sendKeys(request);
        switchToSearchFrame();

        return new SearchFrame();
    }

    public SearchFrame switchToSearchFrame() {
        final SelenideElement searchFrame = $x("//iframe[@class='modal-iframe']")
                .should(and("active", exist, visible), ofSeconds(5));
        switchTo().frame(searchFrame);

        return new SearchFrame();
    }

    public LoginPage clickOnEnterButton() {
        $x("//div[contains(text(),'Вход')]")
                .shouldBe(visible, ofSeconds(5))
                .click();

        return new LoginPage();
    }
}
