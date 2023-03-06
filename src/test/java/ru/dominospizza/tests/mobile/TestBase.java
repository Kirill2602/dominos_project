package ru.dominospizza.tests.mobile;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import ru.dominospizza.drivers.BrowserstackDriver;
import ru.dominospizza.drivers.MobileDriver;
import ru.dominospizza.helpers.Attach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    @BeforeAll
    @DisplayName("Предварительные конфигурации")
    static void setUp() {
        switch (System.getProperty("env")) {
            case "browserstack":
                Configuration.browser = BrowserstackDriver.class.getName();
                break;
            case "local":
                Configuration.browser = MobileDriver.class.getName();
                break;
        }
        Configuration.browserSize = null;
        Configuration.pageLoadTimeout = 60000;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Selenide.open();
    }

    @AfterEach
    void addAttachments() {
        Attach.pageSource();
        String sessionId = Attach.getSessionId();
        switch (System.getProperty("env")) {
            case "browserstack":
                Attach.videoBrowserstack(sessionId);
                Attach.browserstackFullInfoLink(sessionId);
                break;
            case "local":
                Attach.screenshotAs("Last screenshot");
                break;
        }
        closeWebDriver();
    }
}
