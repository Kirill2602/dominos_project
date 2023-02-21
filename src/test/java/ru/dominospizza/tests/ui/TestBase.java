package ru.dominospizza.tests.ui;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import ru.dominospizza.tests.ui.config.Config;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    Config config = ConfigFactory.create(Config.class);


    @BeforeEach
    @DisplayName("Предварительные конфигурации")
    void setUp() {
        if (!config.remote()) {
            Configuration.baseUrl = config.baseUrl();
            Configuration.browserSize = config.browserSize();
            Configuration.timeout = Long.parseLong(config.timeout());
            Configuration.browser = config.browser();
        }
        Configuration.baseUrl = config.baseUrl();
        Configuration.browserSize = config.browserSize();
        Configuration.timeout = Long.parseLong(config.timeout());
        Configuration.browser = config.browser();
        Configuration.remote = config.remoteBrowser();
    }

    @AfterEach
    @DisplayName("Закрыть браузер")
    void tearDown() {
        closeWebDriver();
    }
}
