package ru.dominospizza.tests.mobile.pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.dominospizza.tests.mobile.pages.PizzaPage;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductCardComponent {
    SelenideElement
            productName = $$x("//android.widget.TextView[@text='Пицца-туница']").first(),
            addToCartButton = $x("//android.widget.TextView[@text='Добавить в корзину']");

    @Step("Проверяем название в карточке блюда")
    public ProductCardComponent checkProductName(String text) {
        assertEquals(productName.text(), text);
        return this;
    }

    @Step("Нажать на кнопку 'Добавить в корзину'")
    public PizzaPage clickAddToCartButton() {
        addToCartButton.click();
        return new PizzaPage();
    }

}
