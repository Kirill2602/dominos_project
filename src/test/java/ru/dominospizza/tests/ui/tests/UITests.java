package ru.dominospizza.tests.ui.tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.dominospizza.tests.ui.TestBase;
import ru.dominospizza.tests.ui.pages.MainPage;
import ru.dominospizza.testsdata.TestData;

import java.util.List;
import java.util.stream.Stream;

import static io.qameta.allure.SeverityLevel.*;

public class UITests extends TestBase {
    TestData data = new TestData();
    MainPage mainPage = new MainPage();

    @Test
    @DisplayName("Проверка всех пунктов меню в шапке главной страницы")
    @Tag("ui")
    @Owner("spitsyn.k")
    @Severity(TRIVIAL)
    void checkMainPageHeaderLogoAndNavMenuItems() {
        mainPage
                .checkVisibilityOfHeaderLogo()
                .checkNavMenuItems(data.getNavMenuItemsText());
    }

    @Test
    @DisplayName("Неудачная попытка авторизации с пустыми полями 'Номер телефона' и 'Пароль'")
    @Tag("ui")
    @Owner("spitsyn.k")
    @Severity(NORMAL)
    void unSuccessLoginWithEmptyPhoneNumberAndPassword() {
        mainPage
                .clickLoginButton()
                .checkingForTabsOfForm()
                .chooseAnAuthorizationMethod()
                .incorrectLogin("", "")
                .submitLoginButton()
                .checkPhoneErrorText(data.getExpectedEmptyPhoneErrorText())
                .checkPasswordErrorText(data.getExpectedEmptyPasswordErrorText());
    }

    @Test
    @DisplayName("Неудачная попытка авторизации с пустым номером телефона")
    @Tag("ui")
    @Owner("spitsyn.k")
    @Severity(NORMAL)
    void unSuccessLoginWithEmptyPhoneNumber() {
        mainPage
                .clickLoginButton()
                .checkingForTabsOfForm()
                .chooseAnAuthorizationMethod()
                .incorrectLogin("", "qwe123456")
                .submitLoginButton()
                .checkPhoneErrorText(data.getExpectedEmptyPhoneErrorText());
    }

    @Test
    @DisplayName("Неудачная попытка авторизации с пустым паролем")
    @Tag("ui")
    @Owner("spitsyn.k")
    @Severity(NORMAL)
    void unSuccessLoginWithEmptyPassword() {
        mainPage
                .clickLoginButton()
                .checkingForTabsOfForm()
                .chooseAnAuthorizationMethod()
                .incorrectLogin("89677777277", "")
                .submitLoginButton()
                .checkPasswordErrorText(data.getExpectedEmptyPasswordErrorText());
    }

    @Test
    @DisplayName("Неудачная попытка авторизации с коротким номером телефона")
    @Tag("ui")
    @Owner("spitsyn.k")
    @Severity(NORMAL)
    void unSuccessLoginWithShortPhoneNumber() {
        mainPage
                .clickLoginButton()
                .checkingForTabsOfForm()
                .chooseAnAuthorizationMethod()
                .incorrectLogin("11111111", "qwe123456")
                .submitLoginButton()
                .checkPhoneErrorText(data.getExpectedShortPhoneErrorText());
    }

    @Test
    @DisplayName("Успешная авторизация зарегестрированного пользователя")
    @Tag("ui")
    @Owner("spitsyn.k")
    @Severity(BLOCKER)
    void successLogin() {
        mainPage
                .clickLoginButton()
                .checkingForTabsOfForm()
                .chooseAnAuthorizationMethod()
                .correctLogin("9652294486", "Qwe12345")
                .checkUserName(data.getExpectedUserName());
    }

    @Test
    @DisplayName("Добавление товара в корзину")
    @Tag("ui")
    @Owner("spitsyn.k")
    @Severity(BLOCKER)
    void addProductInCart() {
        mainPage
                .clickOnPizzaLink()
                .checkAvailabilityOfPizzaName("Пицца-туница")
                .clickChoiceButton()
                .clickAddButton()
                .checkQtyInCart()
                .clickCartButton()
                .checkPizzaName("Пицца-туница")
                .checkQtyInCart("1")
                .clickArrangeOrderButton()
                .checkHeaderText("Моя корзина")
                .checkProductNameInDescription("Пицца-туница")
                .checkQty("1")
                .checkVisibilityOfRegisterOrderButton();
    }

    @Test
    @DisplayName("Редактирование кол-ва товара в корзине, проверка цены и удаление товара")
    @Tag("ui")
    @Owner("spitsyn.k")
    @Severity(BLOCKER)
    void changeProductQtyAndCheckPriceInCartAndDeleteProduct() {
        mainPage
                .clickOnPizzaLink()
                .checkAvailabilityOfPizzaName("Пицца-туница")
                .clickChoiceButton()
                .clickAddButton()
                .checkQtyInCart()
                .clickCartButton()
                .clickArrangeOrderButton()
                .checkHeaderText("Моя корзина")
                .checkProductNameInDescription("Пицца-туница")
                .checkQty("1")
                .checkQtyAndPriceAfterClickOnPlusBtn()
                .checkQty("2")
                .deleteProduct()
                .checkVisibilityOfHeaderText()
                .clickYesButton()
                .checkVisibilityOfEmptyCartText();
    }

    static Stream<Arguments> checkProducts() {
        return Stream.of(
                Arguments.of("Новая", List.of("Пицца-половинки", "Охотничья")),
                Arguments.of("Вегетерианская", List.of("Пицца-половинки", "Маргарита Гурме", "4 Сыра", "Веджи Fit & Fresh", "Карамельный Ананас", "Маргарита")),
                Arguments.of("Острая", List.of("Пицца-половинки", "Диабло")),
                Arguments.of("С грибами", List.of("Пицца-половинки", "Домино'c", "Веджи Fit & Fresh", "Чикен BBQ", "Ветчина и грибы")),
                Arguments.of("Без лука", List.of("Пицца-половинки", "Сырная с ветчиной", "Пепперони по-деревенски", "Пепперони", "Маргарита Гурме", "Гавайская", "4 Сыра", "Веджи Fit & Fresh", "Карамельный Ананас")),
                Arguments.of("Мясная", List.of("Пицца-половинки", "Сырная с ветчиной", "Пепперони по-деревенски", "Пепперони", "Домино'c", "Гавайская", "Чикен BBQ", "Ветчина и грибы", "Карбонара"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка наличия в категории \"{0}\"пицц: {1}")
    @Tag("ui")
    void checkProducts(String filterBtnName, List<String> products) {
        mainPage
                .clickOnPizzaLink()
                .clickOnPizzaFilterByCategoryButton()
                .clickOnFilterButton(filterBtnName)
                .checkFilteredProducts(products);
    }
}
