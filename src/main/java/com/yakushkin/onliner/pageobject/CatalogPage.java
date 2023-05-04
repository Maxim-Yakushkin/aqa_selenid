package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.allMatch;
import static com.codeborne.selenide.CollectionCondition.containExactTextsCaseSensitive;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.yakushkin.enumiration.OnlinerBaseUrl.CATALOG_PAGE_URL;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.xpath;

public class CatalogPage extends BasePage {

    private static final String CATALOG_NAVIGATION_CLASSIFIER_ITEM_XPATH = "//li[@class='catalog-navigation-classifier__item ']";
    private static final String CATALOG_CLASSIFIER_XPATH_PATTERN = "//span[@class='catalog-navigation-classifier__item-title-wrapper' and contains(text(),'%s')]";
    private static final String COMPUTER_AND_NETWORKS_VERTICAL_MENU_XPATH_PATTERN = "//div[@class='catalog-navigation-list__aside-title' and normalize-space(text())='%s']";
    private static final String ALL_CATEGORIES_BY_POINT_FROM_VERTICAL_MENU_XPATH_PATTERN = "//div[normalize-space(text())='%s']/parent::*//a/span";
    private static final String CATEGORY_XPATH_PATTERN = "//div[contains(text(),'%s')]/parent::*//span[contains(text(),'%s')]";

    @Override
    public CatalogPage open() {
        navigateTo(CATALOG_PAGE_URL.getUrl());
        return this;
    }

    public ElementsCollection getAllCatalogNavigationClassifiers() {
        final List<String> expectedClassifierTitles = Arrays.asList("Электроника", "Компьютеры и сети",
                "Бытовая техника", "На каждый день", "Стройка и ремонт",
                "Дом и сад", "Авто и мото", "Красота и спорт", "Детям и мамам");

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

    public boolean isComputerAndNetworksVerticalMenuDisplayed() {
        return isElementDisplayed(xpath("//div[contains(@class,'catalog-navigation-list__aside_active')]" +
                                        "/div[@class='catalog-navigation-list__aside-list']"));
    }

    public ElementsCollection getComputerAndNetworksVerticalMenuPoints() {
        final List<String> expectedVerticalMenuPointTitles = Arrays.asList("Ноутбуки, компьютеры, мониторы", "Комплектующие");

        return $$x("//div[contains(@class,'aside catalog-navigation-list__aside_active')]" +
                   "//div[contains(@class,'catalog-navigation-list__aside-list')]" +
                   "//div[contains(@class,'title')]")
                .shouldBe(allMatch("visible", WebElement::isDisplayed))
                .shouldHave(containExactTextsCaseSensitive(expectedVerticalMenuPointTitles));
    }
}
