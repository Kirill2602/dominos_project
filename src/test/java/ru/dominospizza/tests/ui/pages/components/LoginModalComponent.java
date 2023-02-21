package ru.dominospizza.tests.ui.pages.components;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.dominospizza.tests.ui.pages.MainPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginModalComponent {
    SelenideElement
            loginTab = $("[data-testid='authTabView_tab_0']");
    SelenideElement registrationTab = $("[data-testid = 'authTabView_tab_1']");
    SelenideElement authorizationMethod = $x("//form/p/a");
    SelenideElement phoneInput = $("[data-testId='login_form_phone_input']");
    SelenideElement passwordInput = $("[data-testid='login_form_password_input']");
    SelenideElement loginButton = $("[data-testid='login_form_submit_button']");
    SelenideElement emptyPhoneErrorText = phoneInput.parent().sibling(0);
    SelenideElement emptyPasswordErrorText = passwordInput.parent().sibling(0);
    static SelenideElement notificationModal = $("#ins-web-opt-in-reminder-container"),
            spinTheWheelGameModal = $("#ins-web-wheel-of-fortune-adaptive"),
            closeSpinTheWheelButton = $("#close-button-1543162318000");

    @Step("Закрыть или ожидать закрытия модальных окон")
    public static void closeModalsWindows() {
        if (notificationModal.is(appear)) {
            notificationModal.shouldNotBe(visible, Duration.ofSeconds(8000));
        } else if (spinTheWheelGameModal.is(appear)) {
            closeSpinTheWheelButton.click();
        }
    }

    @Step("Проверка наличия вкладок 'Вход' и 'Регистрация'")
    public LoginModalComponent checkingForTabsOfForm() {
        loginTab.shouldBe(visible);
        registrationTab.shouldBe(visible);
        return this;
    }

    @Step("Выбрать авторизационный метод")
    public LoginModalComponent chooseAnAuthorizationMethod() {
        if (authorizationMethod.text().contains("Телефон")) {
            authorizationMethod.click();
        }
        return this;
    }

    @Step("Заполнить поле 'Номер телефона'")
    public LoginModalComponent setPhoneNumber(String phoneNumber) {
        closeModalsWindows();
        phoneInput.click();
        phoneInput.sendKeys(phoneNumber);
        return this;
    }

    @Step("Заполнить поле 'Пароль'")
    public LoginModalComponent setPassword(String password) {
        closeModalsWindows();
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Нажать кнопку 'Войти'")
    public LoginModalComponent submitLoginButton() {
        closeModalsWindows();
        loginButton.submit();
        return this;
    }

    @Step("Проверить текст ошибки пустого номера телефона")
    public LoginModalComponent checkPhoneErrorText(String text) {
        assertEquals(text, emptyPhoneErrorText.text());
        return this;
    }

    @Step("Проверить текст ошибки пустого пароля")

    public LoginModalComponent checkPasswordErrorText(String text) {
        assertEquals(text, emptyPasswordErrorText.text());
        return this;
    }

    @Step("Попытка авторицации с некорректными данными")
    public LoginModalComponent incorrectLogin(String phone, String password) {
        setPhoneNumber(phone);
        setPassword(password);
        submitLoginButton();
        return this;
    }

    @Step("Авторизация зарегестрированного пользователя")
    public MainPage correctLogin(String phone, String password) {
        setPhoneNumber(phone);
        setPassword(password);
        submitLoginButton();
        return new MainPage();
    }
}
