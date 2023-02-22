package ru.dominospizza.tests.api.config;
@Config.LoadPolicy(Config.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources(
        {"system:properties",
                "classpath:config/api/${env}.properties"})
public interface Config extends org.aeonbits.owner.Config {
    @Key("apiBaseUrl")
    String apiBaseUrl();
}
