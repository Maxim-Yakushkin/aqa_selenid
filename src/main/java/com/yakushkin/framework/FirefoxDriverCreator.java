package com.yakushkin.framework;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static com.yakushkin.util.PropertiesReader.getProperty;

public class FirefoxDriverCreator implements WebDriverCreator<RemoteWebDriver> {

    @Override
    public RemoteWebDriver create() throws MalformedURLException {
        if (Boolean.parseBoolean(getProperty("test.remote.mode"))) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();

            // capabilities
            firefoxOptions.setCapability("browserVersion", "112.0");
            firefoxOptions.setCapability("selenoid:options", new HashMap<String, Object>() {{
                put("sessionTimeout", "5m");
                put("enableVNC", true); // visual in session
            }});

            return new RemoteWebDriver(new URL(getProperty("test.remote.url")), firefoxOptions);
        } else {
            return new FirefoxDriver(new FirefoxOptions().addArguments("--remote-allow-origins=*"));
        }
    }
}
