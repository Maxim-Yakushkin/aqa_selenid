package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.allMatch;
import static com.codeborne.selenide.Selenide.$$x;

public class CategoryPage extends BasePage {

    public ElementsCollection getProductCards() {
        return $$x("//div[contains(@class,'schema-product__group')]")
                .shouldBe(allMatch("visible", WebElement::isDisplayed));
    }

    public List<String> getProductCardTitles() {
        return $$x("//span[contains(@data-bind,'product.full_name')]")
                .shouldBe(allMatch("visible", WebElement::isDisplayed))
                .shouldHave(allMatch("text is not blank", el -> !el.getText().isBlank()))
                .texts();
    }

    public List<String> getProductPrices() {
        return $$x("//div[not(contains(@class,'schema-product_children'))]" +
                   "/div[contains(@class,'schema-product__part_2')]" +
                   "/div[contains(@class,'schema-product__part_3')]" +
                   "//span[contains(@data-bind,'root.format.minPrice')]")
                .shouldBe(allMatch("visible", WebElement::isDisplayed))
                .shouldHave(allMatch("text is not blank", el -> !el.getText().isBlank()))
                .texts();
    }

    public List<String> getProductDescriptions() {
        return $$x("//span[contains(@data-bind,'product.description')]")
                .shouldBe(allMatch("visible", WebElement::isDisplayed))
                .shouldHave(allMatch("text is not blank", el -> !el.getText().isBlank()))
                .texts();
    }

    public List<String> getProductRatings() {
        return $$x("//div[@class='schema-product__rating-group']")
                .shouldBe(allMatch("visible", WebElement::isDisplayed))
                .texts();
    }

    public ElementsCollection getProductImages() {
        return $$x("//div[@class='schema-product__group']/div/div/div[@class='schema-product__image']")
                .shouldBe(allMatch("visible", WebElement::isDisplayed));
    }

    public ElementsCollection getProductCheckboxes() {
        return $$x("//div[not(contains(@class,'schema-product_children'))]" +
                   "/div[contains(@class,'schema-product__part_1')]" +
                   "/div[@class='schema-product__compare']")
                .shouldBe(allMatch("visible", WebElement::isDisplayed));
    }
}
