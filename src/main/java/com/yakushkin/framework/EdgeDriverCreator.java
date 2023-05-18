package com.yakushkin.framework;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static com.yakushkin.util.PropertiesReader.getProperty;

public class EdgeDriverCreator implements WebDriverCreator<RemoteWebDriver> {

    @Override
    public RemoteWebDriver create() throws MalformedURLException {
        if (Boolean.parseBoolean(getProperty("test.remote.mode"))) {
            EdgeOptions edgeOptions = new EdgeOptions();

            // arguments
            edgeOptions.addArguments("--remote-allow-origins=*");

            // capabilities
            edgeOptions.setCapability("browserVersion", "112.0");
            edgeOptions.setCapability("selenoid:options", new HashMap<String, Object>() {{
                put("sessionTimeout", "5m");
                put("enableVNC", false); // visual in session
            }});

            return new RemoteWebDriver(new URL(getProperty("test.remote.url")), edgeOptions);
        } else {
            return new EdgeDriver(new EdgeOptions().addArguments("--remote-allow-origins=*"));
        }
    }
}
