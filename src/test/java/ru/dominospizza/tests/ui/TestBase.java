package ru.dominospizza.tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.dominospizza.config.CredentialsConfig;
import ru.dominospizza.config.UiConfig;
import ru.dominospizza.helpers.Attach;
import ru.dominospizza.tests.ui.pages.MainPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static ru.dominospizza.helpers.StringDecoderHelper.decoder;

public class TestBase {
    @BeforeAll
    @DisplayName("Предварительные конфигурации")
    static void setUp() {
        UiConfig uiConfig = ConfigFactory.create(UiConfig.class);
        CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class);
        switch (System.getProperty("env")) {
            case "ui.local":
                Configuration.baseUrl = uiConfig.localBaseUrl();
                Configuration.browserSize = uiConfig.browserSize();
                Configuration.timeout = uiConfig.timeout();
                Configuration.browser = uiConfig.browser();
                break;
            case "ui.remote":
                Configuration.baseUrl = uiConfig.remoteBaseUrl();
                Configuration.browserVersion = uiConfig.browserVersion();
                Configuration.browserSize = uiConfig.browserSize();
                Configuration.timeout = uiConfig.remoteTimeout();
                Configuration.browser = uiConfig.browser();
                Configuration.remote = "https://" + decoder(credentials.userData()) + uiConfig.remoteBrowser();
                Configuration.pageLoadTimeout = uiConfig.pageLoadTimeout();
        }
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    public void addLogger() {
        new MainPage().openPage();
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    public void addAttachments() {
        Attach.pageSource();
        String sessionId = Attach.getSessionId();
        switch (System.getProperty("env")) {
            case "ui.local":
                Attach.screenshotAs("Last screenshot");
                Attach.browserConsoleLogs();
                break;
            case "ui.remote":
                Attach.screenshotAs("Last screenshot");
                Attach.videoSelenoid(sessionId);
                break;
        }
        closeWebDriver();
    }
}