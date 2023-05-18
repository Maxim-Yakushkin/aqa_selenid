package com.yakushkin.framework;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;

public class DriverProvider implements WebDriverProvider {

    @Nonnull
    @Override
    public RemoteWebDriver createDriver(@Nonnull Capabilities capabilities) {

        try {
            return WebDriverCreatorFactory.getDriverCreator(System.getProperty("webDriverName"))
                    .create();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
