package ru.dominospizza.tests.ui;

import com.codeborne.selenide.Configuration;
import com.sun.tools.javac.Main;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.dominospizza.tests.ui.config.Config;
import ru.dominospizza.tests.ui.pages.MainPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    MainPage mainPage = new MainPage();

    @BeforeAll
    @DisplayName("Предварительные конфигурации")
    static void setUp() {
        Config config = ConfigFactory.create(Config.class);
        switch (System.getProperty("env")) {
            case "ui.local":
                Configuration.baseUrl = config.baseUrl();
                Configuration.browserSize = config.browserSize();
                Configuration.timeout = config.timeout();
                Configuration.browser = config.browser();
                break;
            case "ui.remote":
                System.out.println(config.remoteBrowser());
                Configuration.baseUrl = config.baseUrl();
                Configuration.browserVersion = config.browserVersion();
                Configuration.browserSize = config.browserSize();
                Configuration.timeout = config.remoteTimeout();
                Configuration.browser = config.browser();
                Configuration.remote = config.remoteBrowser();
                Configuration.pageLoadTimeout = config.pageLoadTimeout();


                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", true);
                Configuration.browserCapabilities = capabilities;
        }
    }

    @BeforeEach
    void beforeEach() {
        mainPage.openPage();
    }

    @AfterEach
    @DisplayName("Закрыть браузер")
    void tearDown() {
        closeWebDriver();
    }
}
