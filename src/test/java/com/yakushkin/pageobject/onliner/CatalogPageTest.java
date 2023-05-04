package com.yakushkin.pageobject.onliner;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.yakushkin.helper.MessageHelper.ACTUAL_AND_EXPECTED_SIZE_DOESNT_MATH_MESSAGE;
import static com.yakushkin.pageobject.onliner.CatalogPage.getCategoryInfos;
import static com.yakushkin.pageobject.onliner.CatalogPage.getPartOfCategoryInfos;

public class CatalogPageTest {

    private CatalogPage catalogPage;

    @BeforeClass
    void init() {
        catalogPage = new CatalogPage();
    }

    @Test
    void checkThatClassifierMenuContainsNecessaryClassifiers() {
        catalogPage
                .open()
                .getAllCatalogNavigationClassifiers();
    }

    @Test
    void checkThatComputersAndNetworksClassifierContainsNecessaryPointsInVerticalMenu() {
        catalogPage
                .open()
                .clickOnComputersAndNetworksClassifier()
                .hoverToAccessoriesAsideTitle()
                .getComputerAndNetworksVerticalMenuPoints();
    }

    @Test
    void checkThatAllCategoriesInClassifierContainsNecessaryFiledValues() {
        final int indexTitleInCategoryInfo = 0;
        final int indexCountOfItemsInCategoryInfo = 1;
        final int indexMinPriceOfItemInCategoryInfo = 2;

        final ElementsCollection categories = catalogPage
                .open()
                .clickOnComputersAndNetworksClassifier()
                .hoverToAccessoriesAsideTitle()
                .getCategoriesForAccessories();

        final List<String[]> categoryInfos = getCategoryInfos(categories);
        final List<String> categoryTitles = getPartOfCategoryInfos(indexTitleInCategoryInfo, categoryInfos);
        final List<String> categoryCountOfGoods = getPartOfCategoryInfos(indexCountOfItemsInCategoryInfo, categoryInfos);
        final List<String> categoryStartPrice = getPartOfCategoryInfos(indexMinPriceOfItemInCategoryInfo, categoryInfos);

        final SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(categoryTitles).as(ACTUAL_AND_EXPECTED_SIZE_DOESNT_MATH_MESSAGE).hasSameSizeAs(categories);
        softAssertions.assertThat(categoryCountOfGoods).as(ACTUAL_AND_EXPECTED_SIZE_DOESNT_MATH_MESSAGE).hasSameSizeAs(categories);
        softAssertions.assertThat(categoryStartPrice).as(ACTUAL_AND_EXPECTED_SIZE_DOESNT_MATH_MESSAGE).hasSameSizeAs(categories);
        softAssertions.assertAll();
    }

    @Test
    void checkPreviewStructureForEachTradeItemOnPageWithListOfGoods() {
        final CategoryPage categoryPage = catalogPage
                .open()
                .clickOnElectronicsClassifier()
                .hoverToAudioEquipmentAsideTitle()
                .clickOnHeadPhoneCategory();

        categoryPage.getProductCards();
        categoryPage.getProductCardTitles();
        categoryPage.getProductPrices();
        categoryPage.getProductDescriptions();
        categoryPage.getProductRatings();
        categoryPage.getProductImages();
        categoryPage.getProductCheckboxes();
    }
}
