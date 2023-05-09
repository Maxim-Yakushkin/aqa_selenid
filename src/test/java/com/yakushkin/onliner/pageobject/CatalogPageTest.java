package com.yakushkin.onliner.pageobject;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.SoftAsserts;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({SoftAsserts.class})
public class CatalogPageTest {

    private CatalogPage catalogPage;

    @BeforeClass
    void init() {
        Configuration.assertionMode = AssertionMode.STRICT;
        catalogPage = new CatalogPage();
    }

    @Test
    void checkOpenCatalogPage() {
        catalogPage.open();
    }

    @Test
    void checkNavigationTitle() {
        catalogPage
                .open()
                .verifyNavigationTitle();
    }

    @Test
    void checkThatClassifierMenuContainsNecessaryClassifiers() {
        catalogPage
                .open()
                .verifyCatalogNavigationClassifiers();
    }

    @Test
    void checkThatComputersAndNetworksClassifierContainsNecessaryPointsInVerticalMenu() {
        catalogPage
                .open()
                .clickOnComputersAndNetworksClassifier()
                .hoverToAccessoriesAsideTitle()
                .verifyComputerAndNetworksVerticalMenuPoints();
    }

    @Test
    void checkThatAllCategoriesInClassifierContainsNecessaryFiledValues() {
        Configuration.assertionMode = AssertionMode.SOFT;
        catalogPage
                .open()
                .clickOnComputersAndNetworksClassifier()
                .hoverToAccessoriesAsideTitle()
                .verifyCategoriesForAccessories();
    }

    @Test
    void checkPreviewStructureForEachTradeItemOnPageWithListOfGoods() {
        Configuration.assertionMode = AssertionMode.SOFT;
        catalogPage
                .open()
                .clickOnElectronicsClassifier()
                .hoverToAudioEquipmentAsideTitle()
                .clickOnHeadPhoneCategory()
                .verifyProductCards()
                .verifyProductCardTitles()
                .verifyProductPrices()
                .verifyProductDescriptions()
                .verifyProductRatings()
                .verifyProductImages()
                .verifyProductCheckboxes();
    }
}
