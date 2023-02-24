package ru.dominospizza.tests.mobile.tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.dominospizza.tests.mobile.pages.components.OrderReceiptMethodPopUpComponent;
import ru.dominospizza.testsdata.TestData;

public class MobileTests extends OrderReceiptMethodPopUpComponent {
    TestData data = new TestData();
    private static OrderReceiptMethodPopUpComponent methodPopUpComponent;

    @BeforeAll
    public static void init() {
        methodPopUpComponent = new OrderReceiptMethodPopUpComponent();
    }

    @Test
    @DisplayName("Проверка правильного отображения типа услуги и адреса при выборе доставки")
    @Tag("mobile")
    @Owner("spitsyn.k")
    void checkDeliveryServiceAndLocation() {
        methodPopUpComponent
                .checkPopUpText()
                .clickDeliveryButton()
                .clickContinueButton()
                .clickDenyButton()
                .setAddress()
                .clickUseThisAddressButton()
                .checkServiceType(data.getDeliveryService())
                .checkLocation(data.getDeliveryServiceAddress());
    }

    @Test
    @DisplayName("Проверка правильного отображения типа услуги и адреса при выборе самовывоза")
    @Tag("mobile")
    @Owner("spitsyn.k")
    void checkPikUpServiceAndLocation() {
        methodPopUpComponent
                .checkPopUpText()
                .clickPickUpFromTheRestaurantButton()
                .clickDenyButtonAndReturnTakeWay()
                .clickShowRestaurantButton()
                .setRestaurantAddress(data.getPickUpServiceAddress())
                .clickOnFirstRestaurantFromList()
                .clickOnContinueButton()
                .checkServiceType(data.getPickUpService())
                .checkLocation(data.getDeliveryServiceAddress());
    }

    @Test
    @DisplayName("Успешная авторизация зарегестрированного пользователя")
    @Tag("mobile")
    @Owner("spitsyn.k")
    void successLogin() {
        methodPopUpComponent
                .checkPopUpText()
                .clickLoginOrRegisterButton()
                .successLogin("9652294486", "Qwe12345")
                .checkPopUpTextAfterAuth(data.getExpectedUserName());
    }

    @Test
    @DisplayName("Проверка данных в алерте при неудачной попытке авторизоваться не зарегестрированного пользователя")
    @Tag("mobile")
    @Owner("spitsyn.k")
    void unSuccessLogin() {
        methodPopUpComponent
                .checkPopUpText()
                .clickLoginOrRegisterButton()
                .unSuccessLogin("1111111111", "Qwe11111")
                .checkAlertTitle()
                .checkAlertMessage(data.getExpectedIncorrectPhoneOrPasswordErrorText())
                .clickAlertOkButton();
    }

    @Test
    @DisplayName("Проверка добавления товара в корзину, после авторизации зарегестрированного пользователя")
    @Tag("mobile")
    @Owner("spitsyn.k")
    void addProductInCart() {
        methodPopUpComponent
                .checkPopUpText()
                .clickLoginOrRegisterButton()
                .successLogin("9652294486", "Qwe12345")
                .checkPopUpTextAfterAuth(data.getExpectedUserName())
                .clickPickUpFromTheRestaurantButton()
                .clickDenyButtonAndReturnTakeWay()
                .clickShowRestaurantButton()
                .setRestaurantAddress(data.getPickUpServiceAddress())
                .clickOnFirstRestaurantFromList()
                .clickOnContinueButton()
                .clickOnPizzaButton()
                .clickAddButton()
                .checkProductName("Пицца-туница")
                .clickAddToCartButton()
                .clickGoToCartButton()
                .checkProductQty("1")
                .checkProductName("Пицца-туница")
                .checkDeliveryAddress(data.getPickUpServiceAddress())
                .clickOnCartConfirmationButton()
                .setPaymentMethod()
                .setConfirmationCheckBox()
                .checkVisibilityOfConfirmationOrderButton("Завершить заказ");
    }
}
