package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverConditions;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.yakushkin.enumiration.OnlinerBaseUrl.MAIN_PAGE_URL;
import static java.time.Duration.ofSeconds;

public class MainPage extends BasePage {
    private static final String MAIN_NAVIGATION_SECTION_XPATH_PATTERN = "//ul[@class='b-main-navigation']" +
                                                                        "//span[text()='%s']/parent::*";

    @Override
    public MainPage open() {
        navigateTo(MAIN_PAGE_URL.getUrl());
        return this;
    }

    public SelenideElement getNavigationLogo() {
        webdriver()
                .shouldHave(WebDriverConditions.url(MAIN_PAGE_URL.getUrl()))
                .shouldHave(WebDriverConditions.title(MAIN_PAGE_URL.getTitle()));

        return $x("//div[@class='b-top-logo']")
                .shouldBe(exist, visible);
    }

    public CatalogPage goToCatalogSection() {
        $x(String.format(MAIN_NAVIGATION_SECTION_XPATH_PATTERN, "Каталог"))
                .shouldBe(visible, ofSeconds(10))
                .click();
        return new CatalogPage();
    }
}
