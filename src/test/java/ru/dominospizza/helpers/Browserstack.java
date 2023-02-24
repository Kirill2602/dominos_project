package ru.dominospizza.helpers;

import org.aeonbits.owner.ConfigFactory;
import ru.dominospizza.config.Config;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static ru.dominospizza.helpers.CustomApiListener.withCustomTemplates;
import static ru.dominospizza.helpers.StringDecoderHelper.decoder;

public class Browserstack {
    public static String getVideoUrl(String sessionId) {
        Config config = ConfigFactory.create(Config.class);
        String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);
        return given()
                .log().all()
                .filter(withCustomTemplates())
                .auth().basic(decoder(config.login()), decoder(config.password()))
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }
}
