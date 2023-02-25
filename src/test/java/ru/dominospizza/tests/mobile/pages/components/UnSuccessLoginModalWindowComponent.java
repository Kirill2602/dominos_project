package ru.dominospizza.tests.mobile.pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.dominospizza.tests.mobile.pages.LoginPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnSuccessLoginModalWindowComponent {
    SelenideElement
            alertTitle = $(id("android:id/alertTitle")),
            alertMessage = $(id("android:id/message")),
            alertOkButton = $(id("android:id/button1"));

    @Step("Проверить видимость текста тайтла в алерте")
    public UnSuccessLoginModalWindowComponent checkAlertTitle() {
        alertTitle.shouldBe(visible);
        return this;
    }

    @Step("Проверить текст сообщения в алерте")
    public UnSuccessLoginModalWindowComponent checkAlertMessage(String message) {
        assertEquals(alertMessage.text(), message);
        return this;
    }

    @Step("Нажать кнопку 'ОК' в алерте")
    public LoginPage clickAlertOkButton() {
        alertOkButton.click();
        return new LoginPage();
    }
}
