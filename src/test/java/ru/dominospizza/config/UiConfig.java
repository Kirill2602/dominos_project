package ru.dominospizza.config;

@UiConfig.LoadPolicy(UiConfig.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources(
        {"system:properties",
                "classpath:config/ui/${env}.properties"})
public interface UiConfig extends org.aeonbits.owner.Config {
    @Key("localBaseURL")
    String localBaseUrl();

    @Key("remoteBaseURL")
    String remoteBaseUrl();


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
}
