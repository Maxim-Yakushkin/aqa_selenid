package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.title;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.yakushkin.enumiration.OnlinerBaseUrl.MAIN_PAGE;
import static java.time.Duration.ofSeconds;

public class MainPage extends BasePage {
    private static final String MAIN_NAVIGATION_SECTION_XPATH_PATTERN = "//ul[@class='b-main-navigation']" +
                                                                        "//span[text()='%s']/parent::*";

    @Override
    public MainPage open() {
        Selenide.open(MAIN_PAGE.getUrl());
        webdriver()
                .shouldHave(url(MAIN_PAGE.getUrl()))
                .shouldHave(title(MAIN_PAGE.getTitle()));

        return this;
    }

    public SelenideElement getNavigationLogo() {
        webdriver()
                .shouldHave(url(MAIN_PAGE.getUrl()))
                .shouldHave(title(MAIN_PAGE.getTitle()));

        return $x("//div[@class='b-top-logo']")
                .shouldBe(and("presented and displayed", exist, visible), ofSeconds(5));
    }

    public CatalogPage goToCatalogSection() {
        $x(String.format(MAIN_NAVIGATION_SECTION_XPATH_PATTERN, "Каталог"))
                .shouldBe(visible, ofSeconds(10))
                .click();
        return new CatalogPage();
    }
}
