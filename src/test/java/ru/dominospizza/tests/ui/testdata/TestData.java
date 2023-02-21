package ru.dominospizza.tests.ui.testdata;

import lombok.Getter;

import java.util.List;

@Getter
public class TestData {
    private List<String>
            navMenuItemsText = List.of("АКЦИИ", "ПИЦЦА", "ЗАКУСКИ", "ДЕСЕРТЫ", "НАПИТКИ", "СОУСЫ");

    private String
            expectedEmptyPhoneErrorText = "Поле «Телефон» обязательно для заполнения",
            expectedEmptyPasswordErrorText = "Поле «Пароль» обязательно для заполнения",
            expectedShortPhoneErrorText = "Введите правильный телефон.",
            expectedUserName = "Autotest";
}
