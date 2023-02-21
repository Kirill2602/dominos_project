package ru.dominospizza.tests.mobile.helpers;

import org.aeonbits.owner.ConfigFactory;
import ru.dominospizza.tests.mobile.config.Config;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static ru.dominospizza.tests.api.helpers.CustomApiListener.withCustomTemplates;

public class Browserstack {
    public static String getVideoUrl(String sessionId) {
        Config config = ConfigFactory.create(Config.class);
        String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);
        return given()
                .log().all()
                .filter(withCustomTemplates())
//                .auth().basic(config.login(), config.password())
//                .auth().basic("kirill_Av63Rj", "Y2aJHfEasW6DtNsyW6Tz")
                .auth().basic("kirill530", "zyzzGhYFCmx6eB18fu3P")
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }
}
