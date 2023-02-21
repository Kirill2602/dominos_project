package ru.dominospizza.tests.mobile.pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.dominospizza.tests.mobile.pages.MapPage;
import ru.dominospizza.tests.mobile.pages.TakeawayPage;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class DeviceLocationComponent {
    SelenideElement
            denyButton = $(id("com.android.permissioncontroller:id/permission_deny_button"));

    @Step("Нажать кнопку 'Отклонить'")
    public MapPage clickDenyButton() {
        denyButton.click();
        return new MapPage();
    }

    @Step("Нажать кнопку 'Отклонить'")
    public TakeawayPage clickDenyButtonAndReturnTakeWay() {
        denyButton.click();
        return new TakeawayPage();
    }
}
