package ru.dominospizza.tests.ui.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.dominospizza.tests.ui.pages.components.CartComponent;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static ru.dominospizza.tests.ui.pages.components.LoginModalComponent.closeModalsWindows;

public class PizzaPage {
    SelenideElement
            tunaPizzaBlock = $("[data-osgcode='TUNAPZ']"),
            choiceButton = $("[data-testId='product_pizza_1_expand_button']"),
            addButton = $("[data-testId='product_pizza_1_add_button']"),
            qtyInCart = $(".sc-1vciwzu-2.lbpdUQ"),
            cartButton = $("[data-testId='header_cart_toggle_button']"),
            pizzaFilterByCategoryButton = $("[data-testId='pizzafilter_toggle_button']"),
            footerText = $(".sc-1nbwown-0.fLEMQr h1");
    ElementsCollection
            filterButtons = $$(".fl60hy-0.iNLGfO.c8ov00-4.kmNAFE"),
            filteredVisibleProductsList = $$("[itemprop='name']");

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

    @Step("Нажать на кнопку 'Фильтр'")
    public PizzaPage clickOnPizzaFilterByCategoryButton() {
        closeModalsWindows();
        pizzaFilterByCategoryButton.click();
        return this;
    }

    @Step("Выбрать фильтр")
    public PizzaPage clickOnFilterButton(String name) {
        closeModalsWindows();
        filterButtons.findBy(text(name)).click();
        return this;
    }

    @Step("Проверить наличие продуктов подходящих под фильтр")
    public PizzaPage checkFilteredProducts(List<String> productsList) {
        closeModalsWindows();
        filteredVisibleProductsList.shouldHave(CollectionCondition.texts(productsList));
        return this;
    }
}
