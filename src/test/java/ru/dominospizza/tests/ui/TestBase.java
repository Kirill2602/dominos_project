package ru.dominospizza.tests.ui;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.dominospizza.tests.ui.config.Config;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    Config config = ConfigFactory.create(Config.class);

    @BeforeEach
    @DisplayName("Предварительные конфигурации")
    void setUp() {
        switch (System.getProperty("env")) {
            case "ui.local":
                Configuration.baseUrl = config.baseUrl();
                Configuration.browserSize = config.browserSize();
                Configuration.timeout = Long.parseLong(config.timeout());
                Configuration.browser = config.browser();
                break;
            case "ui.remote":
                Configuration.baseUrl = config.baseUrl();
                Configuration.browserVersion = config.browserVersion();
                Configuration.browserSize = config.browserSize();
                Configuration.timeout = Long.parseLong(config.timeout());
                Configuration.browser = config.browser();
                Configuration.remote = config.remoteBrowser();


                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", true);
                Configuration.browserCapabilities = capabilities;
        }
    }

    @AfterEach
    @DisplayName("Закрыть браузер")
    void tearDown() {
        closeWebDriver();
    }
}
