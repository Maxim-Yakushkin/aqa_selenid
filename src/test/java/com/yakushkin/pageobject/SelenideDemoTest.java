package com.yakushkin.pageobject;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.yakushkin.url.OnlinerBaseUrl.MAIN_PAGE_URL;

public class SelenideDemoTest {

    @Test
    void demoTest() {
//        Configuration.browser = Browsers.EDGE;
        open(MAIN_PAGE_URL.getUrl());
    }
}
