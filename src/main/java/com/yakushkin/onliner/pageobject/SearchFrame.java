package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.yakushkin.enumiration.SearchFrameTabs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.allMatch;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.util.Arrays.stream;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.xpath;

public class SearchFrame extends BasePage {

    public ElementsCollection getAllSearchTabItems() {
        return $$x("//div[contains(@class,'search__tabs-item')]")
                .shouldBe(allMatch("all elements displayed", WebElement::isDisplayed))
                .shouldHave(allMatch("text for each element is not blank", el -> !el.getText().isBlank()))
                .shouldHave(size(SearchFrameTabs.values().length), Duration.ofSeconds(5))
                .shouldHave(exactTexts(stream(SearchFrameTabs.values()).map(SearchFrameTabs::getName).toList()));
    }

    public SearchFrame clickOnNews() {
        getAllSearchTabItems()
                .filter(Condition.text(SearchFrameTabs.IN_NEWS.getName()))
                .first()
                .click();

        return this;
    }

    public SearchFrame clickInNewsOnVideoFilterCheckbox() {
        $x("//span[contains(@class,'i-checkbox search__filter-checkbox')]/span")
                .shouldBe(visible, Duration.ofSeconds(5))
                .click();

        return this;
    }

    public ElementsCollection getCatalogSearchResultCategories() {
        int indexOfCategoryName = 0;
        int indexOfCountOfItems = 1;

        return $$x("//ul[@class='search__results']//div[contains(@class,'result__item_category')]")
                .shouldBe(allMatch("all elements displayed", WebElement::isDisplayed))
                .shouldHave(allMatch("text for each element is not blank", el -> !el.getText().isBlank()))
                .shouldHave(allMatch("category name is not blank", el ->
                        !el.getText().split("\n")[indexOfCategoryName].isBlank()))
                .shouldHave(allMatch("info about count of items is not blank and contains key word", el ->
                        !el.getText().split("\n")[indexOfCountOfItems].isBlank() &&
                        el.getText().split("\n")[indexOfCountOfItems].toLowerCase().contains("товар")));
    }

    public ElementsCollection getNewsSearchResultWithVideo() {
        return $$x("//div[contains(@class,'result__item result__item_news')]")
                .shouldBe(allMatch("displayed", WebElement::isDisplayed))
                .shouldHave(allMatch("contains preview and data", el ->
                        el.findElement(className("news__preview")).isDisplayed() &&
                        el.findElement(className("news__data")).isDisplayed()));
    }
}
