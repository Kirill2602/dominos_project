package ru.dominospizza.config;

import org.aeonbits.owner.Config;

@ru.dominospizza.config.ApiConfig.LoadPolicy(ru.dominospizza.config.ApiConfig.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources(
        {"system:properties",
                "classpath:config/api/${env}.properties"})
public interface ApiConfig extends Config {
    @Key("apiBaseUrl")
    String apiBaseUrl();
}
