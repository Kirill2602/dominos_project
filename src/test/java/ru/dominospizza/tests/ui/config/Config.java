package ru.dominospizza.tests.ui.config;

@org.aeonbits.owner.Config.LoadPolicy(org.aeonbits.owner.Config.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources(
        {"system:properties",
                "classpath:config/ui/${env}.properties"})

public interface Config extends org.aeonbits.owner.Config {
    @Key("baseUrl")
    String baseUrl();

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
