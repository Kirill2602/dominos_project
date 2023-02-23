package ru.dominospizza.tests.mobile.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.dominospizza.tests.mobile.config.Config;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

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
//        mutableCapabilities.setCapability("browserstack.user", config.login());
//        mutableCapabilities.setCapability("browserstack.user", "kirill_Av63Rj");
        mutableCapabilities.setCapability("browserstack.user", "avtotestavtotest_UNn3rV");
//        mutableCapabilities.setCapability("browserstack.key", config.password());
//        mutableCapabilities.setCapability("browserstack.key", "Y2aJHfEasW6DtNsyW6Tz");
        mutableCapabilities.setCapability("browserstack.key", "VNuAjap7MVFQH8JjzunN");
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
