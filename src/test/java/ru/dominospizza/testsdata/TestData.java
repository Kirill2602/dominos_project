package ru.dominospizza.testsdata;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class TestData {
    private String authorization =
            "Bearer eyJhbGciOiJBMTI4S1c" +
                    "iLCJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwidHlwIjoiSldUIn0." +
                    "HsR7SyjkMjrxfabS0r5NYWKcaHEdjRHOPwsaDSNMQnt1Xix6go5w" +
                    "Nw.iDBjS-7TWymz1lkOAw6CaQ.Dg7kpPc3LeTF4bPRcjRfSA_ZLFbKB" +
                    "tGWKpKpe5DsP8kONhu9yPqIQiUF2-q3iG1hfMt6xaWP8zhRg_60czDUgJIGb" +
                    "0gRlh90XsS_fA-1ADG8mF6xB6g0GlsAufrTuAgrgHf8bW9xMKTwuzC8f_h3bGkIV" +
                    "TvFjKwAZKtBPnZywk5b-yenFiqPyCXTg6QoK8k8CCrBZGVsKtH6sWNayEGCJSrqlOB" +
                    "ysKvySM42i_IKrzONL6ICqwes6pVEmpmogBS4yJjFzzsudO_POd0MpBiJ-pC3M-8xeQnNQeX" +
                    "_qt2MMYpqpYrbBaJBdS_BrO1lhyfx0dRsrpFXkYGIPRz2hTdZA8AJFOyz9H1O_fgX08q2NNt6xTa" +
                    "AjHzzUrve5hXOVfq4XXBwZj5Wo5iM9tuuhXk_iq_sKXNyHpLtdP13CJXYqeKL-FboblBNTRqy34ZDj" +
                    "4v74wQJn7o3t_nsi1ibM2qvbY5wjHeegGMcy-KZrHLnRNM.R4xQl_M_jj5CsZjTtA6N6w";

    private Map<String, String>
            correctUserData = Map.of(
            "correctPhoneNumber", "9652294486",
            "id", "PRD_0",
            "name", "Autotest",
            "status", "VerifiedCustomer"
    ),

    incorrectUserData = Map.of(
            "incorrectPhoneNumber", "965229448",
            "status", "InvalidPhoneNumber",
            "errorType", "Yellow",
            "exType", "BadRequest",
            "message", "Ваш номер телефона неверен, пожалуйста, проверьте номер"),

    correctAddProductData = Map.of(
            "status", "Success",
            "error", "false",
            "errorType", "Yellow",
            "exType", "Success",
            "message", "Операция прошла успешно (1)"),

    incorrectAddProductData = Map.of(
            "status", "Error",
            "error", "true",
            "errorType", "Red",
            "exType", "UnexpectedError",
            "message", "Ошибка"),

    zeroQtyAddProductData = Map.of(
            "status", "Error",
            "error", "true",
            "errorType", "Yellow",
            "exType", "UnexpectedError",
            "message", "Продукт отсутствует. Пожалуйста, выберите другой продукт"
    );

    private String
            deliveryService = "Доставка",
            deliveryServiceAddress = "Москва, 2-я Владимирская улица, Москва, 52к2",
            pickUpService = "Навынос",
            pickUpServiceAddress = "3-я Владимирская",
            expectedEmptyPhoneErrorText = "Поле «Телефон» обязательно для заполнения",
            expectedEmptyPasswordErrorText = "Поле «Пароль» обязательно для заполнения",
            expectedShortPhoneErrorText = "Введите правильный телефон.",
            expectedIncorrectPhoneOrPasswordErrorText = "Телефон или пароль недействителен.",
            expectedUserName = "Autotest _!";

    private List<String>
            navMenuItemsText = List.of("АКЦИИ", "ПИЦЦА", "ЗАКУСКИ", "ДЕСЕРТЫ", "НАПИТКИ", "СОУСЫ");
}
