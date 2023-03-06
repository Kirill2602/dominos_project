package ru.dominospizza.config;

import org.aeonbits.owner.Config;

@ru.dominospizza.config.CredentialsConfig.LoadPolicy(ru.dominospizza.config.CredentialsConfig.LoadType.MERGE)
@org.aeonbits.owner.Config.Sources(
        {"system:properties",
                "classpath:config/credentials/credentials.properties",})
public interface CredentialsConfig extends Config {
    @Key("userData")
    String userData();

    @Key("login")
    String login();

    @Key("password")
    String password();
}
