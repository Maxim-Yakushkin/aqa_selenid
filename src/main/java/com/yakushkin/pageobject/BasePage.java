package com.yakushkin.pageobject;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.yakushkin.framework.DriverManager;
import com.yakushkin.util.UtilWebElement;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;

@Data
public abstract class BasePage {

//    private final WebDriver driver;
    private final UtilWebElement utilWebElement;

    public BasePage() {
        DriverManager.initDriver(Browsers.FIREFOX);
        this.utilWebElement = new UtilWebElement();
    }

    public BasePage open() {
        navigateTo("");
        return this;
    }

    public void navigateTo(String url) {
        Selenide.open(url);
    }

    public WebElement findElementWithWaiting(By by) {
        final WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public List<WebElement> findElementsWithWaiting(By by) {
        final WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(60));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public boolean isElementDisplayed(By by) {
        final WebElement element = findElementWithWaiting(by);
        return element.isDisplayed();
    }

    public boolean isElementDisplayed(WebElement webElement) {
        return webElement.isDisplayed();
    }

    public void areElementsDisplayed(By by) {
        final List<WebElement> elements = findElementsWithWaiting(by);
        assertThat(elements)
                .as("Some elements are not displayed.")
                .allMatch(this::isElementDisplayed);
    }

    public void clickOnElement(By by) {
        final WebElement element = findElementWithWaiting(by);
        element.click();
    }

}
