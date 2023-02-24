package ru.dominospizza.testbase;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import ru.dominospizza.config.Config;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.dominospizza.drivers.BrowserstackDriver;
import ru.dominospizza.drivers.MobileDriver;
import ru.dominospizza.helpers.Attach;
import ru.dominospizza.tests.ui.pages.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static ru.dominospizza.helpers.Attach.getSessionId;
import static ru.dominospizza.helpers.StringDecoderHelper.decoder;

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
                Configuration.remote = "https://" + decoder(config.userData()) + config.remoteBrowser();
                Configuration.pageLoadTimeout = config.pageLoadTimeout();

                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", true);
                Configuration.browserCapabilities = capabilities;
            case "browserstack":
                Configuration.browser = BrowserstackDriver.class.getName();
                Configuration.pageLoadTimeout = 60000;
                Configuration.browserSize = null;
                Configuration.timeout = 30000;
                break;
            case "local":
                Configuration.browser = MobileDriver.class.getName();
                Configuration.pageLoadTimeout = 60000;
                Configuration.browserSize = null;
                Configuration.timeout = 30000;
                break;
        }
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        if (System.getProperty("env").contains("ui")) {
            mainPage.openPage();
        } else if (System.getProperty("env").equals("browserstack") || System.getProperty("env").equals("local")) {
            open();
        }
    }

    @AfterEach
    @DisplayName("Закрыть браузер")
    void tearDown() {
        String sessionId = sessionId().toString();
        if (System.getProperty("env").contains("ui")) {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            Attach.videoSelenoid(getSessionId());
            closeWebDriver();
        } else if (System.getProperty("env").equals("browserstack") || System.getProperty("env").equals("local")) {
            Attach.pageSource();
            closeWebDriver();
            Attach.addVideo(sessionId);
        }
    }
}

