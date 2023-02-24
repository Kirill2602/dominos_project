package ru.dominospizza.tests.mobile.pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.dominospizza.testbase.TestBase;
import ru.dominospizza.tests.mobile.pages.LoginPage;
import ru.dominospizza.tests.mobile.pages.MapPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderReceiptMethodPopUpComponent extends TestBase {
    SelenideElement
            authWelcomeText = $x("//android.widget.TextView[@text='Привет Autotest _!']"),
            welcomeText = $x("//android.widget.TextView[@text='Каким образом вы бы хотели получить пиццу?']"),
            deliveryButton = $(accessibilityId("delivery_button_view")),
            pickUpFromTheRestaurantButton = $(accessibilityId("carryout_button_view")),
            loginOrRegisterButton = $(accessibilityId("landing_login_view"));

    @Step("Проверить текст в шапке PopUp")
    public OrderReceiptMethodPopUpComponent checkPopUpText() {
        welcomeText.shouldBe(visible);
        return this;
    }

    @Step("Проверить текст в шапке PopUp, после авторизации")
    public OrderReceiptMethodPopUpComponent checkPopUpTextAfterAuth(String text) {
        authWelcomeText.shouldBe(visible);
        assertTrue(authWelcomeText.text().contains(text));
        return this;
    }

    @Step("Нажать кнопку 'Доставить по адресу'")
    public MapPage clickDeliveryButton() {
        deliveryButton.click();
        return new MapPage();
    }

    @Step("Забрать из ресторана")
    public DeviceLocationComponent clickPickUpFromTheRestaurantButton() {
        pickUpFromTheRestaurantButton.click();
        return new DeviceLocationComponent();
    }

    @Step("Нажать кнопку 'Войти/Зарегестироваться'")
    public LoginPage clickLoginOrRegisterButton() {
        loginOrRegisterButton.click();
        return new LoginPage();
    }
}
