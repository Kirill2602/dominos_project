package ru.dominospizza.tests.ui.pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.dominospizza.tests.ui.pages.CartPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartComponent {
    SelenideElement pizzaName = $(".rdac2o-2.hIeGLp"),
            qtyInCart = $(".sc-9lmyig-2.hWFDze"),
            arrangeOrderButton = $("[data-testId='header_cart_makeorder_button']");

    @Step("Проверить название пиццы")
    public CartComponent checkPizzaName(String name) {
        pizzaName.shouldHave(text(name));
        return this;
    }

    @Step("Проверить кол-во товара")
    public CartComponent checkQtyInCart(String qty) {
        assertEquals(qty, qtyInCart.getValue());
        return this;
    }

    @Step("Нажать кнопку 'Оформить заказ'")
    public CartPage clickArrangeOrderButton() {
        arrangeOrderButton.click();
        return new CartPage();
    }
}
