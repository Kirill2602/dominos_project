package ru.dominospizza.tests.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.dominospizza.tests.ui.pages.components.LoginModalComponent;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPage {

    public MainPage() {
        open("https://podolsk.dominospizza.ru/");
    }

    SelenideElement headerLogo = $("[data-testId='header_logo']"),
            loginButton = $("[data-testId='topbar_user_loginbutton']"),
            userIcon = $x("//div[@class='sc-1inhxcy-5 lcFAbi']"),
            pizzaLink = $("[data-testId='main_nav_item_1']");
    ElementsCollection
            navMenuItems = $$x("//nav[@class='sc-1inhxcy-11 hqUcMX']/ul/li/a");

    @Step("Проверка видимости логотипа на главной странице")
    public MainPage checkVisibilityOfHeaderLogo() {
        headerLogo.shouldBe(visible);
        return this;
    }

    @Step("Проверка корректного отображения всех пунктов меню на главной странице")
    public MainPage checkNavMenuItems(List<String> items) {
        for (int i = 0; i < items.size(); i++) {
            assertEquals(items.get(i), navMenuItems.get(i).text());
        }
        return this;
    }

    @Step("Нажать кнопку войти")
    public LoginModalComponent clickLoginButton() {
        loginButton.click();
        return new LoginModalComponent();
    }

    @Step("Проверка отображения имени пользователя")
    public MainPage checkUserName(String userName) {
        userIcon.shouldBe(visible);
        assertEquals(userName, userIcon.text());
        return this;
    }

    @Step("Нажать на линк 'Пицца'")
    public PizzaPage clickOnPizzaLink() {
        pizzaLink.click();
        return new PizzaPage();
    }
}
