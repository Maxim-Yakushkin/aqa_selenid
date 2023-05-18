package com.yakushkin.framework;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static com.yakushkin.util.PropertiesReader.getProperty;

public class ChromeDriverCreator implements WebDriverCreator<RemoteWebDriver> {

    @Override
    public RemoteWebDriver create() throws MalformedURLException {
        if (Boolean.parseBoolean(getProperty("test.remote.mode"))) {
            ChromeOptions chromeOptions = new ChromeOptions();

            // arguments
            chromeOptions.addArguments("--remote-allow-origins=*");

            // capabilities
            chromeOptions.setCapability("browserVersion", "112.0");
            chromeOptions.setCapability("selenoid:options", new HashMap<String, Object>() {{
                put("sessionTimeout", "5m");
                put("enableVNC", false); // visual in session
            }});

            return new RemoteWebDriver(new URL(getProperty("test.remote.url")), chromeOptions);
        } else {
            return new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
        }
    }
}
