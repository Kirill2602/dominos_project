package ru.dominospizza.tests.mobile.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class RestaurantListPage {
    SelenideElement
            searchInput = $(accessibilityId("store_list_search_input")),
            firstRestaurantFromList = $(accessibilityId("90416")),
            continueButton = $(accessibilityId("store_list_continue_button_view"));

    @Step("Заполнить поле адрес")
    public RestaurantListPage setRestaurantAddress(String address) {
        searchInput.sendKeys(address);
        return this;
    }

    @Step("Нажать на первый ресторан из списка")
    public RestaurantListPage clickOnFirstRestaurantFromList() {
        firstRestaurantFromList.click();
        return this;
    }

    @Step("Нажать на кнопку 'Продолжить'")
    public MainPage clickOnContinueButton() {
        continueButton.click();
        return new MainPage();
    }
}
