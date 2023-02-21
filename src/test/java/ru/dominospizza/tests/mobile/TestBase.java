package ru.dominospizza.tests.mobile;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.dominospizza.tests.mobile.drivers.BrowserstackDriver;
import ru.dominospizza.tests.mobile.drivers.MobileDriver;
import ru.dominospizza.tests.mobile.helpers.Attach;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sessionId;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        switch (System.getProperty("env")) {
            case "browserstack":
                Configuration.browser = BrowserstackDriver.class.getName();
                break;
            case "local":
                Configuration.browser = MobileDriver.class.getName();
                break;
        }
        Configuration.timeout = 30000;
        Configuration.pageLoadTimeout = 60000;
        Configuration.browserSize = null;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachments() {
        String sessionId = sessionId().toString();
        Attach.pageSource();
        Selenide.closeWebDriver();
        if (!System.getProperty("env").equals("local")) Attach.addVideo(sessionId);
    }
}
