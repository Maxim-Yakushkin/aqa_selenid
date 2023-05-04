package com.yakushkin.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class UtilWebElement {

//    private final WebDriver driver;

//    public UtilWebElement() {
//        this.driver = DriverManager.getWebDriver();
//    }

    public void moveToElement(WebElement element) {
        new Actions(getWebDriver())
                .moveToElement(element)
                .perform();
    }

    public static List<String> getTextFromWebElementList(List<WebElement> webElements) {
        return webElements.stream()
                .map(WebElement::getText)
                .toList();
    }
}
