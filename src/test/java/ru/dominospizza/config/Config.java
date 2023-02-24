package ru.dominospizza.config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources(
        {"system:properties",
                "classpath:config/api/${env}.properties",
                "classpath:config/mobile/${env}.properties",
                "classpath:config/ui/${env}.properties"})
public interface Config extends org.aeonbits.owner.Config {
    @Key("apiBaseUrl")
    String apiBaseUrl();

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

    @Key("mobileBaseURL")
    String mobileBaseURL();

    @Key("browserSize")
    String browserSize();

    @Key("timeout")
    Long timeout();

    @Key("browser")
    String browser();

    @Key("remoteBrowser")
    String remoteBrowser();

    @Key("browserVersion")
    String browserVersion();

    @Key("pageLoadTimeout")
    Long pageLoadTimeout();

    @Key("remoteTimeout")
    Long remoteTimeout();

    @Key("userData")
    String userData();
}
