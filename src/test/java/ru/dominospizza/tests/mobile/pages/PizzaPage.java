package ru.dominospizza.tests.mobile.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.dominospizza.tests.mobile.pages.components.CartComponent;
import ru.dominospizza.tests.mobile.pages.components.ProductCardComponent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static ru.dominospizza.drivers.BrowserstackHelper.browserstackScrollDown;
import static ru.dominospizza.drivers.EmulatorHelper.scrollDown;

public class PizzaPage {
    SelenideElement
            firstPizzaFromList = $x("(//android.view.ViewGroup[@content-desc='campaign_order_view'])[3]"),
            addButton = $x("(//android.view.ViewGroup[@content-desc='TUNAPZ.Пицца'])[2]"),
            goToCartButton = $x("//android.view.ViewGroup[@content-desc='tab_bar_cart']/android.widget.TextView[2]");

    @Step("Прокрутить страницу до нужного элемента и нажать кнопку 'Добавить'")
    public ProductCardComponent clickAddButton() {
        if (System.getProperty("env").equals("local")) {
            scrollDown();
        } else if (System.getProperty("env").equals("browserstack")) {
            browserstackScrollDown();
        }
        firstPizzaFromList.shouldBe(visible);
        addButton.click();
        return new ProductCardComponent();
    }

    @Step("Нажать на кнопку 'Перейти в корзину'")
    public CartComponent clickGoToCartButton() {
        goToCartButton.click();
        return new CartComponent();
    }
}
