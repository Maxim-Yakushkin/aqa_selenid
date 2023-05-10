package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.yakushkin.framework.DriverManager.initDriver;
import static java.time.Duration.ofSeconds;

@Data
public abstract class BasePage {

    public BasePage() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
        initDriver();
    }

    public BasePage open() {
        Selenide.open(StringUtils.EMPTY);
        return this;
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

    public LoginForm clickOnEnterButton() {
        $x("//div[contains(text(),'Вход')]")
                .shouldBe(visible, ofSeconds(5))
                .click();

        return new LoginForm();
    }
}
