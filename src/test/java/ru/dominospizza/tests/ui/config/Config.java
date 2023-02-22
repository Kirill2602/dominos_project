package ru.dominospizza.tests.ui.config;

@ru.dominospizza.tests.mobile.config.Config.LoadPolicy(ru.dominospizza.tests.mobile.config.Config.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources(
        {"system:properties",
                "classpath:configs/ui/${env}.properties"})

public interface Config extends org.aeonbits.owner.Config {
    @Key("baseUrl")
    String baseUrl();

    @Key("browserSize")
    String browserSize();

    @Key("timeout")
    String timeout();

    @Key("browser")
    String browser();

    @Key("remote")
    boolean remote();

    @Key("remoteBrowser")
    String remoteBrowser();
}
