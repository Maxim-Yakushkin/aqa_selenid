package com.yakushkin.framework;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;

public interface WebDriverCreator<T extends RemoteWebDriver> {

    T create() throws MalformedURLException;
}
