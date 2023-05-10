package com.yakushkin.onliner.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.CollectionCondition.allMatch;
import static com.codeborne.selenide.Selenide.$$x;

public class CategoryPage extends BasePage {

    @Step("verify the product cards are visible")
    public CategoryPage verifyProductCards() {
        $$x("//div[contains(@class,'schema-product__group')]")
                .shouldBe(allMatch("visible", WebElement::isDisplayed));

        return this;
    }

    @Step("verify the product card titles are visible and not blank")
    public CategoryPage verifyProductCardTitles() {
        $$x("//span[contains(@data-bind,'product.full_name')]")
                .shouldBe(allMatch("visible", WebElement::isDisplayed))
                .shouldHave(allMatch("text is not blank", el -> !el.getText().isBlank()));

        return this;
    }

    @Step("verify the product card start prices are visible and not blank")
    public CategoryPage verifyProductPrices() {
        $$x("//div[not(contains(@class,'schema-product_children'))]" +
            "/div[contains(@class,'schema-product__part_2')]" +
            "/div[contains(@class,'schema-product__part_3')]" +
            "//span[contains(@data-bind,'root.format.minPrice')]")
                .shouldBe(allMatch("visible", WebElement::isDisplayed))
                .shouldHave(allMatch("text is not blank", el -> !el.getText().isBlank()));

        return this;
    }

    @Step("verify the product card descriptions are visible and not blank")
    public CategoryPage verifyProductDescriptions() {
        $$x("//span[contains(@data-bind,'product.description')]")
                .shouldBe(allMatch("visible", WebElement::isDisplayed))
                .shouldHave(allMatch("text is not blank", el -> !el.getText().isBlank()));

        return this;
    }

    @Step("verify the product card ratings are visible")
    public CategoryPage verifyProductRatings() {
        $$x("//div[@class='schema-product__rating-group']")
                .shouldBe(allMatch("visible", WebElement::isDisplayed));

        return this;
    }

    @Step("verify the product card images are visible")
    public CategoryPage verifyProductImages() {
        $$x("//div[@class='schema-product__group']/div/div/div[@class='schema-product__image']")
                .shouldBe(allMatch("visible", WebElement::isDisplayed));

        return this;
    }

    @Step("verify the product card checkboxes are visible")
    public CategoryPage verifyProductCheckboxes() {
        $$x("//div[not(contains(@class,'schema-product_children'))]" +
            "/div[contains(@class,'schema-product__part_1')]" +
            "/div[@class='schema-product__compare']")
                .shouldBe(allMatch("visible", WebElement::isDisplayed));

        return this;
    }
}
