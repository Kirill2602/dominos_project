package ru.dominospizza.config;

import org.aeonbits.owner.Config;

@ru.dominospizza.config.MobileConfig.LoadPolicy(ru.dominospizza.config.MobileConfig.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources(
        {"system:properties",
                "classpath:config/mobile/${env}.properties",})
public interface MobileConfig extends Config {
    @Key("appURL")
    String appUrl();

    @Key("device")
    String device();

    @Key("os_version")
    String osVersion();

    @Key("project")
    String projectName();

    @Key("build")
    String buildName();

    @Key("name")
    String testName();

    @Key("local.version")
    String localVersion();

    @Key("local.device")
    String localDevice();

    @Key("local.app.path")
    String localAppPath();

    @Key("local.platformName")
    String localPlatformName();

    @Key("local.apppackage")
    String localAppPackage();

    @Key("local.appactivity")
    String localAppActivity();

    @Key("local.url")
    String localUrl();

    @Key("mobileBaseURL")
    String mobileBaseURL();
}
