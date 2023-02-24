package ru.dominospizza.drivers;

import com.codeborne.selenide.WebDriverProvider;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.dominospizza.config.Config;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

import static ru.dominospizza.helpers.StringDecoderHelper.decoder;

public class BrowserstackDriver implements WebDriverProvider {
    public static RemoteWebDriver browserstackDriver;
    static Config config = ConfigFactory.create(Config.class);

    @SneakyThrows
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        mutableCapabilities.setCapability("app", config.appUrl());
        mutableCapabilities.setCapability("device", config.device());
        mutableCapabilities.setCapability("os_version", config.osVersion());
        mutableCapabilities.setCapability("browserstack.user", decoder(config.login()));
        mutableCapabilities.setCapability("browserstack.key", decoder(config.password()));
        mutableCapabilities.setCapability("project", config.projectName());
        mutableCapabilities.setCapability("build", config.buildName());
        mutableCapabilities.setCapability("name", config.testName());
        browserstackDriver = new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
        return browserstackDriver;
    }

    public static URL getBrowserstackUrl() {
        try {
            return new URL(config.baseUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
