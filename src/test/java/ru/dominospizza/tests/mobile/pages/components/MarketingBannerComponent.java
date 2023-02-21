package ru.dominospizza.tests.mobile.pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.dominospizza.tests.mobile.pages.MapPage;

import static com.codeborne.selenide.Selenide.$x;

public class MarketingBannerComponent {
    SelenideElement closeBannerButton = $x("/hierarchy/android.widget.FrameLayout" +
            "/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/" +
            "android.webkit.WebView/android.view.View/android.widget.Image");

    @Step("Нажать на кнопку закрытия рекламы")
    public MapPage clickCloseBannerButton() {
        closeBannerButton.click();
        return new MapPage();
    }
}
