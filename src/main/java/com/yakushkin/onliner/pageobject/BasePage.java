package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.yakushkin.framework.DriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.yakushkin.framework.DriverManager.initApplicationDefaultDriver;
import static java.time.Duration.ofSeconds;

@Data
public abstract class BasePage {

    public BasePage() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
        initApplicationDefaultDriver();
    }

    public BasePage open() {
        Selenide.open(StringUtils.EMPTY);
        return this;
    }

    @Step("typing to search line the next request '{request}'")
    public SearchFrame typingIntoSearchLine(String request) {
        $x("//input[@class='fast-search__input']")
                .shouldBe(visible, ofSeconds(5))
                .sendKeys(request);
        switchToSearchFrame();

        return new SearchFrame();
    }

    @Step("switch to search frame")
    public SearchFrame switchToSearchFrame() {
        final SelenideElement searchFrame = $x("//iframe[@class='modal-iframe']")
                .should(and("active", exist, visible), ofSeconds(5));
        switchTo().frame(searchFrame);

        return new SearchFrame();
    }

    @Step("click on 'Вход' button")
    public LoginForm clickOnEnterButton() {
        $x("//div[contains(text(),'Вход')]")
                .shouldBe(visible, ofSeconds(5))
                .click();

        return new LoginForm();
    }
}
