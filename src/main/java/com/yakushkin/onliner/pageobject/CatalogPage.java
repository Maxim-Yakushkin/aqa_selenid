package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.Selenide;
import com.yakushkin.enumiration.CatalogNavigationClassifier;
import com.yakushkin.enumiration.ComputerAndNetworksVerticalMenuPoint;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.allMatch;
import static com.codeborne.selenide.CollectionCondition.containExactTextsCaseSensitive;
import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.title;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.yakushkin.enumiration.OnlinerBaseUrl.CATALOG_PAGE;
import static java.time.Duration.ofSeconds;
import static java.util.Arrays.stream;
import static org.openqa.selenium.By.className;

public class CatalogPage extends BasePage {

    private static final String CATALOG_NAVIGATION_CLASSIFIER_ITEM_XPATH = "//li[@class='catalog-navigation-classifier__item ']";
    private static final String CATALOG_CLASSIFIER_XPATH_PATTERN = "//span[@class='catalog-navigation-classifier__item-title-wrapper' and contains(text(),'%s')]";
    private static final String COMPUTER_AND_NETWORKS_VERTICAL_MENU_XPATH_PATTERN = "//div[@class='catalog-navigation-list__aside-title' and normalize-space(text())='%s']";
    private static final String ALL_CATEGORIES_BY_POINT_FROM_VERTICAL_MENU_XPATH_PATTERN = "//div[normalize-space(text())='%s']/parent::*//a/span";
    private static final String CATEGORY_XPATH_PATTERN = "//div[contains(text(),'%s')]/parent::*//span[contains(text(),'%s')]";

    @Override
    public CatalogPage open() {
        Selenide.open(CATALOG_PAGE.getUrl());
        webdriver()
                .shouldHave(url(CATALOG_PAGE.getUrl()))
                .shouldHave(title(CATALOG_PAGE.getTitle()));

        return this;
    }

    public CatalogPage verifyNavigationTitle() {
        webdriver()
                .shouldHave(url(CATALOG_PAGE.getUrl()))
                .shouldHave(title(CATALOG_PAGE.getTitle()));

        $x("//div[@class='catalog-navigation__title']")
                .shouldBe(and("presented and displayed", exist, visible), ofSeconds(5))
                .shouldHave(text("Каталог"));

        return this;
    }

    public CatalogPage verifyCatalogNavigationClassifiers() {
        final List<String> expectedClassifierTitles = stream(CatalogNavigationClassifier.values())
                .map(CatalogNavigationClassifier::getTitle)
                .toList();

        $$x(CATALOG_NAVIGATION_CLASSIFIER_ITEM_XPATH)
                .shouldBe(allMatch("visible", WebElement::isDisplayed), ofSeconds(10))
                .shouldHave(containExactTextsCaseSensitive(expectedClassifierTitles));

        return this;
    }

    public CatalogPage clickOnComputersAndNetworksClassifier() {
        $x(String.format(CATALOG_CLASSIFIER_XPATH_PATTERN, "Компьютеры"))
                .shouldBe(visible, ofSeconds(10))
                .click();
        return this;
    }


    public CatalogPage clickOnElectronicsClassifier() {
        $x(String.format(CATALOG_CLASSIFIER_XPATH_PATTERN, "Электроника"))
                .shouldBe(visible, ofSeconds(10))
                .click();
        return this;
    }

    public CategoryPage clickOnHeadPhoneCategory() {
        $x(String.format(CATEGORY_XPATH_PATTERN, "Аудиотехника", "Наушники"))
                .shouldBe(visible, ofSeconds(10))
                .click();
        return new CategoryPage();
    }

    public CatalogPage hoverToAccessoriesAsideTitle() {
        $x(String.format(COMPUTER_AND_NETWORKS_VERTICAL_MENU_XPATH_PATTERN, "Комплектующие"))
                .shouldBe(visible, ofSeconds(10))
                .hover();
        return this;
    }

    public CatalogPage hoverToAudioEquipmentAsideTitle() {
        $x(String.format(COMPUTER_AND_NETWORKS_VERTICAL_MENU_XPATH_PATTERN, "Аудиотехника"))
                .shouldBe(visible, ofSeconds(10))
                .hover();
        return this;
    }

    public CatalogPage verifyCategoriesForAccessories() {
        $$x(String.format(ALL_CATEGORIES_BY_POINT_FROM_VERTICAL_MENU_XPATH_PATTERN, "Комплектующие"))
                .shouldBe(allMatch("visible", WebElement::isDisplayed))
                .shouldHave(allMatch("title is not blank", element ->
                        !element.findElement(className("catalog-navigation-list__dropdown-title")).getText().isBlank()))
                .shouldHave(allMatch("count of goods is not blank", element ->
                        !element.findElement(className("catalog-navigation-list__dropdown-description"))
                                .getText().split("\n")[0].isBlank())) // 0 - index of count of goods in description
                .shouldHave(allMatch("start price of goods is not blank", element ->
                        !element.findElement(className("catalog-navigation-list__dropdown-description"))
                                .getText().split("\n")[1].isBlank())); // 1 - index of start price in description

        return this;
    }

    public CatalogPage verifyComputerAndNetworksVerticalMenuPoints() {
        final List<String> expectedVerticalMenuPointTitles = stream(ComputerAndNetworksVerticalMenuPoint.values())
                .map(ComputerAndNetworksVerticalMenuPoint::getName)
                .toList();

        $$x("//div[contains(@class,'aside catalog-navigation-list__aside_active')]" +
            "//div[contains(@class,'catalog-navigation-list__aside-list')]" +
            "//div[contains(@class,'title')]")
                .shouldBe(allMatch("visible", WebElement::isDisplayed))
                .shouldHave(containExactTextsCaseSensitive(expectedVerticalMenuPointTitles));

        return this;
    }
}
