package ru.dominospizza.tests.mobile.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.dominospizza.tests.mobile.pages.components.OrderReceiptMethodPopUpComponent;
import ru.dominospizza.tests.mobile.pages.components.UnSuccessLoginModalWindowComponent;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class LoginPage {
    SelenideElement
            phoneNumberInput = $(accessibilityId("login_form_phone_input")),
            passwordInput = $(accessibilityId("login_form_password_input")),
            continueButton = $(accessibilityId("login_continue_button_view"));

    @Step("Ввести номер телефона")
    public LoginPage setPhoneNumber(String phoneNumber) {
        phoneNumberInput.sendKeys(phoneNumber);
        return this;
    }

    @Step("Ввести пароль")
    public LoginPage setPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Ввести номер телефона и пароль зарегестрированного пользователя и успешно авторизоваться")
    public OrderReceiptMethodPopUpComponent successLogin(String phone, String password) {
        setPhoneNumber(phone);
        setPassword(password);
        continueButton.click();
        return new OrderReceiptMethodPopUpComponent();
    }

    @Step("Ввести номер телефона и пароль не зарегестрированного пользователя и проверить данные в алерте")
    public UnSuccessLoginModalWindowComponent unSuccessLogin(String phone, String password) {
        setPhoneNumber(phone);
        setPassword(password);
        continueButton.click();
        return new UnSuccessLoginModalWindowComponent();
    }
}

