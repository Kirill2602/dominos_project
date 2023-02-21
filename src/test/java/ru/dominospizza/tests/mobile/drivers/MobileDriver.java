package ru.dominospizza.tests.mobile.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import ru.dominospizza.tests.mobile.config.Config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class MobileDriver implements WebDriverProvider {
    public static AndroidDriver driver;
    static Config config = ConfigFactory.create(Config.class);

    public static URL getAppiumServerUrl() {
        try {
            return new URL(config.localUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(config.localPlatformName())
                .setDeviceName(config.localDevice())
                .setPlatformVersion(config.localVersion())
//                .setDeviceName("RFCR90ZMNQP")
//                .setPlatformVersion("13.0")
                .setApp(getAppPath())
                .setAppPackage(config.localAppPackage())
                .setAppActivity(config.localAppActivity());
        driver = new AndroidDriver(getAppiumServerUrl(), options);
        return driver;
    }

    private String getAppPath() {
        String appUrl = "https://www.dropbox.com/s/vhm643mlmkfv3gc/dominos.apk?dl=1";
        String appPath = "src/test/resources/apps/app-alpha-universal-release.apk";

        File app = new File(appPath);
        if (!app.exists()) {
            try (InputStream in = new URL(appUrl).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app.getAbsolutePath();
    }
}

