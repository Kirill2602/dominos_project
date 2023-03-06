package ru.dominospizza.tests.ui.pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.dominospizza.tests.ui.pages.CartPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DeleteQuestionModalComponent {
    SelenideElement popupText = $("[data-testId='alertpopup_content_text']"),
            yesButton = $("[data-testId='alertpopup_options_button_1']");

    @Step("Убедиться в видимости текста")
    public DeleteQuestionModalComponent checkVisibilityOfHeaderText() {
        popupText.shouldBe(visible);
        return this;
    }

    @Step("Нажать на кнопку 'Да'")
    public CartPage clickYesButton() {
        yesButton.click();
        return new CartPage();
    }
}
