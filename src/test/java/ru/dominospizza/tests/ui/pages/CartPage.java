package ru.dominospizza.tests.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.dominospizza.tests.ui.pages.components.DeleteQuestionModalComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.dominospizza.tests.ui.pages.components.LoginModalComponent.closeModalsWindows;

public class CartPage {
    SelenideElement headerText = $(".m6wpoo-1.gAaLTr"),
            orderDescription = $(".ylx9k7-0.gIltOr"),
            qtyInCart = $(".ylx9k7-1.brbHMP div"),
            registerOrderButton = $("[data-testId='basket_footer_button']"),
            plusButton = $("[data-testId='basket_single_increase_PRD_0']"),
            minusButton = $("[data-testId='basket_single_decrease_PRD_0']"),
            emptyCartText = $x("//p[text()='Положите что-нибудь в корзину.']");
    String initialProductQty = qtyInCart.getText(),
            initialOrderPrice = $x("//button[@data-testId='basket_footer_button']/span/span").getText();

    @Step("Проверить текст в заголовке")
    public CartPage checkHeaderText(String text) {
        assertEquals(text, headerText.getText());
        return this;
    }

    @Step("Проверить название товара в описании")
    public CartPage checkProductNameInDescription(String name) {
        orderDescription.shouldHave(text(name));
        return this;
    }

    @Step("Проверить кол-во товара")
    public CartPage checkQty(String qty) {
        qtyInCart.shouldHave(text(qty));
        return this;
    }

    @Step("Проверить видимость кнопки 'Оформить заказ'")
    public CartPage checkVisibilityOfRegisterOrderButton() {
        registerOrderButton.scrollTo().shouldBe(visible);
        return this;
    }

    @Step("Нажать кнопку '+', проверить цену и кол-во товара")
    public CartPage checkQtyAndPriceAfterClickOnPlusBtn() {
        plusButton.click();
        qtyInCart.shouldHave(text(Integer.toString(parseInt(initialProductQty) + 1)));
        initialOrderPrice.equals(Integer.toString(parseInt(initialOrderPrice) * 2));
        return this;
    }

    @Step("Нажать кнопку '-', проверить цену и кол-во товара")
    public DeleteQuestionModalComponent deleteProduct() {
        closeModalsWindows();
        for (int i = 0; i < 2; i++) {
            closeModalsWindows();
            minusButton.click();
            sleep(3000);
        }
        return new DeleteQuestionModalComponent();
    }

    @Step("Проверить текст пустой корзины")
    public CartPage checkVisibilityOfEmptyCartText() {
        emptyCartText.shouldBe(visible);
        return this;
    }
}
