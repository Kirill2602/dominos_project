package ru.dominospizza.drivers;

import com.codeborne.selenide.WebDriverProvider;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.dominospizza.config.CredentialsConfig;
import ru.dominospizza.config.MobileConfig;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

import static ru.dominospizza.helpers.StringDecoderHelper.decoder;

public class BrowserstackDriver implements WebDriverProvider {
    public static RemoteWebDriver browserstackDriver;
    static MobileConfig config = ConfigFactory.create(MobileConfig.class);
    static CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class);

    @SneakyThrows
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        mutableCapabilities.setCapability("app", config.appUrl());
        mutableCapabilities.setCapability("device", config.device());
        mutableCapabilities.setCapability("os_version", config.osVersion());
        mutableCapabilities.setCapability("browserstack.user", decoder(credentials.login()));
        mutableCapabilities.setCapability("browserstack.key", decoder(credentials.password()));
        mutableCapabilities.setCapability("project", config.projectName());
        mutableCapabilities.setCapability("build", config.buildName());
        mutableCapabilities.setCapability("name", config.testName());
        browserstackDriver = new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
        return browserstackDriver;
    }

    public static URL getBrowserstackUrl() {
        try {
            return new URL(config.mobileBaseURL());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
