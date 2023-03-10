package ru.dominospizza.tests.mobile.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.dominospizza.tests.mobile.pages.components.DeviceLocationComponent;
import ru.dominospizza.testsdata.TestData;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class MapPage {
    TestData data = new TestData();
    SelenideElement
            continueButton = $(accessibilityId("Продолжить_view")),
            searchInput = $x("//android.widget.EditText[@index='1']"),
            useThisAddressButton = $x("//android.view.ViewGroup[@content-desc='Использовать данный адрес_view']");

    @Step("Нажать кнопку 'Продолжить'")
    public DeviceLocationComponent clickContinueButton() {
        continueButton.click();
        return new DeviceLocationComponent();
    }

    @Step("Вписать в строку поиска адрес")
    public MapPage setAddress() {
        searchInput.sendKeys(data.getDeliveryServiceAddress());
        sleep(3000);
        searchInput.pressEnter();
        return this;
    }

    @Step("Нажать кнопку 'Использовать данный адрес'")
    public MainPage clickUseThisAddressButton() {
        sleep(4000);
        useThisAddressButton.shouldNotBe(disabled).click();
        return new MainPage();
    }
}
