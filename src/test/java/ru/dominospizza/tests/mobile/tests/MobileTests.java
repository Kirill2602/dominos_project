package ru.dominospizza.tests.mobile.tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import ru.dominospizza.tests.mobile.pages.components.OrderReceiptMethodPopUpComponent;

import static ru.dominospizza.tests.mobile.testdata.TestData.deliveryData;
import static ru.dominospizza.tests.mobile.testdata.TestData.pickupData;

public class MobileTests extends OrderReceiptMethodPopUpComponent {
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
//                .clickCloseBannerButton()
                .clickContinueButton()
                .clickDenyButton()
                .setAddress()
                .clickUseThisAddressButton()
                .checkServiceType("Доставка")
                .checkLocation(deliveryData.get("Доставка"));
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
                .setRestaurantAddress(pickupData.get("Навынос"))
                .clickOnFirstRestaurantFromList()
                .clickOnContinueButton()
                .checkServiceType("Навынос")
                .checkLocation(pickupData.get("Навынос"));
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
                .checkPopUpTextAfterAuth("Autotest _!");
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
                .checkAlertMessage("Телефон или пароль недействителен.")
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
                .checkPopUpTextAfterAuth("Autotest _!")
                .clickPickUpFromTheRestaurantButton()
                .clickDenyButtonAndReturnTakeWay()
                .clickShowRestaurantButton()
                .setRestaurantAddress(pickupData.get("Навынос"))
                .clickOnFirstRestaurantFromList()
                .clickOnContinueButton()
                .clickOnPizzaButton()
                .clickAddButton()
                .checkProductName("Пицца-туница")
                .clickAddToCartButton()
                .clickGoToCartButton()
                .checkProductQty("1")
                .checkProductName("Пицца-туница")
                .checkDeliveryAddress(pickupData.get("Навынос"))
                .clickOnCartConfirmationButton()
                .setPaymentMethod()
                .setConfirmationCheckBox()
                .checkVisibilityOfConfirmationOrderButton("Завершить заказ");
    }
}
