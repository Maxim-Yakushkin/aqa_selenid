package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverConditions;
import com.yakushkin.enumiration.CatalogNavigationClassifier;
import com.yakushkin.enumiration.ComputerAndNetworksVerticalMenuPoint;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.allMatch;
import static com.codeborne.selenide.CollectionCondition.containExactTextsCaseSensitive;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.yakushkin.enumiration.OnlinerBaseUrl.CATALOG_PAGE_URL;
import static java.time.Duration.ofSeconds;
import static java.util.Arrays.stream;

public class CatalogPage extends BasePage {

    private static final String CATALOG_NAVIGATION_CLASSIFIER_ITEM_XPATH = "//li[@class='catalog-navigation-classifier__item ']";
    private static final String CATALOG_CLASSIFIER_XPATH_PATTERN = "//span[@class='catalog-navigation-classifier__item-title-wrapper' and contains(text(),'%s')]";
    private static final String COMPUTER_AND_NETWORKS_VERTICAL_MENU_XPATH_PATTERN = "//div[@class='catalog-navigation-list__aside-title' and normalize-space(text())='%s']";
    private static final String ALL_CATEGORIES_BY_POINT_FROM_VERTICAL_MENU_XPATH_PATTERN = "//div[normalize-space(text())='%s']/parent::*//a/span";
    private static final String CATEGORY_XPATH_PATTERN = "//div[contains(text(),'%s')]/parent::*//span[contains(text(),'%s')]";

    @Override
    public CatalogPage open() {
        navigateTo(CATALOG_PAGE_URL.getUrl());
        webdriver()
                .shouldHave(WebDriverConditions.url(CATALOG_PAGE_URL.getUrl()))
                .shouldHave(WebDriverConditions.title(CATALOG_PAGE_URL.getTitle()));

        return this;
    }

    public SelenideElement getNavigationTitle() {
        webdriver()
                .shouldHave(WebDriverConditions.url(CATALOG_PAGE_URL.getUrl()))
                .shouldHave(WebDriverConditions.title(CATALOG_PAGE_URL.getTitle()));

        return $x("//div[@class='catalog-navigation__title']")
                .shouldBe(exist, visible)
                .shouldHave(text("Каталог"));
    }

    public ElementsCollection getAllCatalogNavigationClassifiers() {
        final List<String> expectedClassifierTitles = stream(CatalogNavigationClassifier.values())
                .map(CatalogNavigationClassifier::getTitle)
                .toList();

        return $$x(CATALOG_NAVIGATION_CLASSIFIER_ITEM_XPATH)
                .shouldBe(allMatch("visible", WebElement::isDisplayed), ofSeconds(10))
                .shouldHave(containExactTextsCaseSensitive(expectedClassifierTitles));
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

    public ElementsCollection getCategoriesForAccessories() {
        return $$x(String.format(ALL_CATEGORIES_BY_POINT_FROM_VERTICAL_MENU_XPATH_PATTERN, "Комплектующие"))
                .shouldBe(allMatch("visible", WebElement::isDisplayed));
    }

    public static List<String[]> getCategoryInfos(ElementsCollection categories) {
        return categories.texts().stream()
                .map(description -> description.split("\n"))
                .toList();
    }

    public static List<String> getPartOfCategoryInfos(int indexOfDescriptionPart, List<String[]> categories) {
        return categories.stream()
                .map(description -> description[indexOfDescriptionPart])
                .filter(partOfDescription -> !partOfDescription.isBlank())
                .toList();
    }

    public ElementsCollection getComputerAndNetworksVerticalMenuPoints() {
        final List<String> expectedVerticalMenuPointTitles = stream(ComputerAndNetworksVerticalMenuPoint.values())
                .map(ComputerAndNetworksVerticalMenuPoint::getName)
                .toList();

        return $$x("//div[contains(@class,'aside catalog-navigation-list__aside_active')]" +
                   "//div[contains(@class,'catalog-navigation-list__aside-list')]" +
                   "//div[contains(@class,'title')]")
                .shouldBe(allMatch("visible", WebElement::isDisplayed))
                .shouldHave(containExactTextsCaseSensitive(expectedVerticalMenuPointTitles));
    }
}
