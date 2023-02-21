package ru.dominospizza.tests.mobile.pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.dominospizza.tests.mobile.drivers.BrowserstackHelper.browserstackScrollDown;
import static ru.dominospizza.tests.mobile.drivers.EmulatorHelper.scrollDown;

public class CartComponent {
    SelenideElement
            deliveryAddress = $x("//android.widget.TextView[@text='3-я Владимирская']"),
            productName = $x("//android.widget.TextView[@text='Пицца-туница']"),
            qtyProduct = $(accessibilityId("quantity_text_input")),

    cartConfirmationButton = $(accessibilityId("cart_confirmation_button")),
            confirmationOrderButton = $x("//android.view.ViewGroup[@resource-id='confirmation_order_button']"),
            paymentMethod = $x("//android.view.ViewGroup[@content-desc='4']/android.view.ViewGroup[1]"),
            confirmationCheckBox = $x("//android.view.ViewGroup[@content-desc='confirmation_aggrement_checkbox']");

    @Step("Проверить адрес доставки в корзине")
    public CartComponent checkDeliveryAddress(String address) {
        assertEquals(deliveryAddress.text(), address);
        return this;
    }

    @Step("Проверить название блюда в корзине")
    public CartComponent checkProductName(String name) {
        assertEquals(productName.text(), name);
        return this;
    }

    @Step("Проверить кол-во блюд в корзине")
    public CartComponent checkProductQty(String qty) {
        assertEquals(qtyProduct.text(), qty);
        return this;
    }

    @Step("Нажать на кнопку 'Подтвердить корзину'")
    public CartComponent clickOnCartConfirmationButton() {
        cartConfirmationButton.click();
        return this;
    }

    @Step("Выбрать способ оплаты")
    public CartComponent setPaymentMethod() {
        paymentMethod.click();
        return this;
    }

    @Step("Проставить галку о принятии пользовательского соглашения")
    public CartComponent setConfirmationCheckBox() {
        if (System.getProperty("env").equals("local")) {
            scrollDown();
        } else if(System.getProperty("env").equals("browserstack")){
            browserstackScrollDown();
        }
        confirmationCheckBox.click();
        return this;
    }

    @Step("Проверить видимость кнопки 'Завершить заказ'")
    public void checkVisibilityOfConfirmationOrderButton(String text) {
        assertEquals(confirmationOrderButton.text(), text);
    }
}
