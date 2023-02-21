package ru.dominospizza.tests.api.tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.dominospizza.tests.api.endpoints.EndPoints;
import ru.dominospizza.tests.api.models.addproductincart.AddProductInCart;
import ru.dominospizza.tests.api.models.addproductincart.Product;
import ru.dominospizza.tests.api.models.login.successlogin.SuccessLogin;
import ru.dominospizza.tests.api.models.login.unsuccesslogin.UnSuccessLogin;
import ru.dominospizza.tests.api.specifications.Specs;
import ru.dominospizza.tests.api.testdata.TestData;

import java.util.ArrayList;

import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class ApiTests {
    TestData data = new TestData();
    SuccessLogin response;
    UnSuccessLogin incorrectResponse;
    AddProductInCart addProductInCart;

    @Test
    @Tag("api")
    @Owner("spitsyn.k")
    @DisplayName("Авторизация зарегестрированного пользователя")
    @Severity(CRITICAL)
    void successLogin() {
        step("Авторизация зарегестрированного пользователя", () -> {
            response = given(Specs.requestSpec)
                    .header("authorization", data.getAuthorization())
                    .when()
                    .get(EndPoints.AUTH_ENDPOINT + data.getCorrectUserData().get("correctPhoneNumber"))
                    .then()
                    .spec(Specs.responseSpec(200))
                    .extract().as(SuccessLogin.class);
        });
        step("Проверка номера телефона в ответе", () -> {
            assertEquals(data.getCorrectUserData().get("correctPhoneNumber"), response.getData().getPhoneNumber());
        });
        step("Проверка имени в ответе", () -> {
            assertEquals(data.getCorrectUserData().get("name"), response.getData().getName());
        });
        step("Проверка верификации номера телефона в ответе", () -> {
            assertTrue(response.getData().isPhoneVerified());
        });
        step("Проверка статуса в ответе", () -> {
            assertEquals(data.getCorrectUserData().get("status"), response.getStatus());
        });
    }

    @Test
    @Tag("api")
    @Owner("spitsyn.k")
    @DisplayName("Авторизация пользователя с коротким номером телефона")
    @Severity(CRITICAL)
    void loginWithIncorrectPhoneNumber() {
        step("Попытка авторизации пользователя с коротким номером телефона", () -> {
            incorrectResponse = given(Specs.requestSpec)
                    .header("authorization", data.getAuthorization())
                    .when()
                    .get(EndPoints.AUTH_ENDPOINT + data.getIncorrectUserData().get("incorrectPhoneNumber"))
                    .then()
                    .spec(Specs.responseSpec(400))
                    .extract().as(UnSuccessLogin.class);
        });

        step("Проверка статуса в ответе", () -> {
            assertEquals(data.getIncorrectUserData().get("status"), incorrectResponse.getStatus());
        });
        step("Проверка типа ошибки в ответе", () -> {
            assertEquals(data.getIncorrectUserData().get("errorType"), incorrectResponse.getErrorType());
        });
        step("Проверка наличия ошибки в ответе", () -> {
            assertTrue(incorrectResponse.isError());
        });
        step("Проверка поля 'exType' в ответе", () -> {
            assertEquals(data.getIncorrectUserData().get("exType"), incorrectResponse.getExType());
        });
        step("Проверка текста ошибки в ответе", () -> {
            assertEquals(data.getIncorrectUserData().get("message"), incorrectResponse.getMessage());
        });
    }

    @Test
    @Tag("api")
    @Owner("spitsyn.k")
    @DisplayName("Добавление товара в корзину")
    @Severity(CRITICAL)
    void addProductInCart() {
        Product product = new Product("TUNA13T", "TUNAPZ", "14", "THIN", "NONE", "Pizza", 10, new ArrayList<>(), false);
        step("Добавляем товар в корзину ", () -> {
            addProductInCart = given(Specs.requestSpec)
                    .header("authorization", data.getAuthorization())
                    .body(product)
                    .when()
                    .post(EndPoints.ADD_PRODUCT_IN_CART)
                    .then()
                    .spec(Specs.responseSpec(200))
                    .extract().as(AddProductInCart.class);
        });
        step("Проверка статуса в ответе", () -> {
            assertEquals(data.getCorrectAddProductData().get("status"), addProductInCart.getStatus());
        });
        step("Проверка отсутствия ошибки в ответе", () -> {
            assertFalse(addProductInCart.isError());
        });
        step("Проверка типа ошибки в ответе", () -> {
            assertEquals(data.getCorrectAddProductData().get("errorType"), addProductInCart.getErrorType());
        });
        step("Проверка поля 'exType' в ответе", () -> {
            assertEquals(data.getCorrectAddProductData().get("exType"), addProductInCart.getExType());
        });
        step("Проверка текста сообщения в ответе", () -> {
            assertEquals(data.getCorrectAddProductData().get("message"), addProductInCart.getMessage());
        });
    }

    @Test
    @Tag("api")
    @Owner("spitsyn.k")
    @DisplayName("Добавление большого кол-ва товара в корзину")
    @Severity(CRITICAL)
    void addMaxQtyProductInCart() {
        Product product = new Product("TUNA13T", "TUNAPZ", "14", "THIN", "NONE", "Pizza", 1000, new ArrayList<>(), false);
        step("Добавляем товар в корзину ", () -> {
            addProductInCart = given(Specs.requestSpec)
                    .header("authorization", data.getAuthorization())
                    .body(product)
                    .when()
                    .post(EndPoints.ADD_PRODUCT_IN_CART)
                    .then()
                    .spec(Specs.responseSpec(500))
                    .extract().as(AddProductInCart.class);
        });
        step("Проверка статуса в ответе", () -> {
            assertEquals(data.getIncorrectAddProductData().get("status"), addProductInCart.getStatus());
        });
        step("Проверка наличия ошибки в ответе", () -> {
            assertTrue(addProductInCart.isError());
        });
        step("Проверка типа ошибки в ответе", () -> {
            assertEquals(data.getIncorrectAddProductData().get("errorType"), addProductInCart.getErrorType());
        });
        step("Проверка поля 'exType' в ответе", () -> {
            assertEquals(data.getIncorrectAddProductData().get("exType"), addProductInCart.getExType());
        });
        step("Проверка текста сообщения в ответе", () -> {
            assertEquals(data.getIncorrectAddProductData().get("message"), addProductInCart.getMessage());
        });
    }

    @Test
    @Tag("api")
    @Owner("spitsyn.k")
    @DisplayName("Добавление 0 шт товара в корзину")
    @Severity(CRITICAL)
    void addZeroQtyProductInCart() {
        Product product = new Product("TUNA13T", "TUNAPZ", "14", "THIN", "NONE", "Pizza", 0, new ArrayList<>(), false);
        step("Добавляем товар в корзину в кол-ве 0 шт", () -> {
            addProductInCart = given(Specs.requestSpec)
                    .header("authorization", data.getAuthorization())
                    .body(product)
                    .when()
                    .post(EndPoints.ADD_PRODUCT_IN_CART)
                    .then()
                    .spec(Specs.responseSpec(500))
                    .extract().as(AddProductInCart.class);
        });
        step("Проверка статуса в ответе", () -> {
            assertEquals(data.getZeroQtyAddProductData().get("status"), addProductInCart.getStatus());
        });
        step("Проверка наличия ошибки в ответе", () -> {
            assertTrue(addProductInCart.isError());
        });
        step("Проверка типа ошибки в ответе", () -> {
            assertEquals(data.getZeroQtyAddProductData().get("errorType"), addProductInCart.getErrorType());
        });
        step("Проверка поля 'exType' в ответе", () -> {
            assertEquals(data.getZeroQtyAddProductData().get("exType"), addProductInCart.getExType());
        });
        step("Проверка текста сообщения в ответе", () -> {
            assertEquals(data.getZeroQtyAddProductData().get("message"), addProductInCart.getMessage());
        });
    }
}
