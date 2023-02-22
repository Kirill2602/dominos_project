package ru.dominospizza.tests.mobile.config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources(
        {"system:properties",
                "classpath:configs/mobile/${env}.properties",
                "classpath:credentials.properties"})
public interface Config extends org.aeonbits.owner.Config {
    @Key("login")
    String login();

    @Key("password")
    String password();

    @Key("baseURL")
    String baseUrl();

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
}