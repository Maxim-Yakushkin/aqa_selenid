package com.yakushkin.pageobject.onliner;

import com.yakushkin.pageobject.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.yakushkin.url.OnlinerBaseUrl.MAIN_PAGE_URL;
import static java.time.Duration.ofSeconds;

public class MainPage extends BasePage {
    private static final String MAIN_NAVIGATION_SECTION_XPATH_PATTERN = "//ul[@class='b-main-navigation']" +
                                                                        "//span[text()='%s']/parent::*";

    @Override
    public MainPage open() {
        navigateTo(MAIN_PAGE_URL.getUrl());
        return this;
    }

    public CatalogPage goToCatalogSection() {
        $x(String.format(MAIN_NAVIGATION_SECTION_XPATH_PATTERN, "Каталог"))
                .shouldBe(visible, ofSeconds(10))
                .click();
        return new CatalogPage();
    }
}
