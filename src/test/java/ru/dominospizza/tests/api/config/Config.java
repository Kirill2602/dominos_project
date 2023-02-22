package ru.dominospizza.tests.api.config;

@org.aeonbits.owner.Config.Sources(
        {"system:properties",
                "classpath:${env}.properties"})
public interface Config extends org.aeonbits.owner.Config {
    @Key("apiBaseUrl")
    String apiBaseUrl();
}
