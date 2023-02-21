package ru.dominospizza.tests.api.models.addproductincart;

import lombok.Getter;

@Getter
public class AddProductInCart {
    private String status;
    private boolean error;
    private String errorType;
    private String exType;
    private String message;
    private String stackTrace;
}
