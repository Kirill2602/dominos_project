package ru.dominospizza.tests.api.models.login.successlogin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessLogin {
    private String status;
    private Data data;
    private boolean error;
    private String exType;
    private String message;
    private String stackTrace;
}
