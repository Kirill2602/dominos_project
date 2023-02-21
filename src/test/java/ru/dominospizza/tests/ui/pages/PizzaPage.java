package ru.dominospizza.tests.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.dominospizza.tests.ui.pages.components.CartComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static ru.dominospizza.tests.ui.pages.components.LoginModalComponent.closeModalsWindows;

public class PizzaPage {
    SelenideElement
            tunaPizzaBlock = $("[data-osgcode='TUNAPZ']"),
            choiceButton = $("[data-testId='product_pizza_1_expand_button']"),
            addButton = $("[data-testId='product_pizza_1_add_button']"),
            qtyInCart = $(".sc-1vciwzu-2.lbpdUQ"),
            cartButton = $("[data-testId='header_cart_toggle_button']");

    @Step("Проверить наличие названия выбранной пиццы")
    public PizzaPage checkAvailabilityOfPizzaName(String name) {
        closeModalsWindows();
        tunaPizzaBlock.scrollTo().shouldHave(text(name));
        return this;
    }

    @Step("Нажать кнопку 'Выбрать'")
    public PizzaPage clickChoiceButton() {
        closeModalsWindows();
        choiceButton.click();
        return this;
    }

    @Step("Нажать кнопку 'В корзину'")
    public PizzaPage clickAddButton() {
        closeModalsWindows();
        addButton.click();
        return this;
    }

    @Step("Проверить кол-во товаров в корзине")
    public PizzaPage checkQtyInCart() {
        closeModalsWindows();
        qtyInCart.shouldHave(text("1"));
        return this;
    }

    @Step("Нажать на кнопку корзины")
    public CartComponent clickCartButton() {
        closeModalsWindows();
        cartButton.click();
        return new CartComponent();
    }
}
