package ru.dominospizza.tests.mobile.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class TakeawayPage {
    SelenideElement
            showRestaurantButton = $(accessibilityId("carryout_form_submit_button_view"));

    @Step("Нажать кнопку 'Показать рестораны'")
    public RestaurantListPage clickShowRestaurantButton() {
        showRestaurantButton.click();
        return new RestaurantListPage();
    }
}
