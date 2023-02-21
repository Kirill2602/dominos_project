package ru.dominospizza.tests.api.models.login.unsuccesslogin;

import lombok.Getter;

@Getter
public class UnSuccessLogin {
    private String status;
    private boolean error;
    private String errorType;
    private String exType;
    private String message;
    private String stackTrace;
}
