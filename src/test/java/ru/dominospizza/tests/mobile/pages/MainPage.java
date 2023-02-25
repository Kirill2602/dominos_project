package ru.dominospizza.tests.mobile.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage {
    SelenideElement
            serviceType = $x("//android.view.ViewGroup[@content-desc='home_service_type']/android.widget.TextView[1]"),
            location = $x("//android.view.ViewGroup[@content-desc='home_location']/android.widget.TextView[1]"),
            pizzaButton = $x("(//android.widget.TextView[@text='Пицца']");

    @Step("Проверить тип обслуживания в хедере")
    public MainPage checkServiceType(String value) {
        assertTrue(serviceType.text().contains(value));
        return this;
    }

    @Step("Проверить адрес в хедере")
    public MainPage checkLocation(String value) {
        assertTrue(value.contains(location.text()));
        return this;
    }

    @Step("Нажать на кнопку 'Пицца'")
    public PizzaPage clickOnPizzaButton() {
        refresh();
        pizzaButton.click();
        return new PizzaPage();
    }
}
