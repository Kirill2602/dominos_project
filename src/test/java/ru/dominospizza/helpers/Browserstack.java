package ru.dominospizza.helpers;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import ru.dominospizza.config.CredentialsConfig;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static ru.dominospizza.helpers.StringDecoderHelper.decoder;

public class Browserstack {
    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);

    public static String videoUrl(String sessionId) {
        return getSessionInfo(sessionId)
                .path("automation_session.video_url");
    }
    public static String getVideoUrl(String sessionId) {
        String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);
            return getSessionInfo(sessionId)
                    .path("automation_session.video_url");
        }

        public static String fullInfoPublicUrl(String sessionId) {
            return getSessionInfo(sessionId)
                    .path("automation_session.public_url");
        }

        private static ExtractableResponse<Response> getSessionInfo(String sessionId) {
            return given()
                    .auth().basic(decoder(config.login()), decoder(config.password()))
                    .when()
                    .get("https://api-cloud.browserstack.com/app-automate/sessions/" + sessionId +".json")
                    .then()
                    .statusCode(200)
                    .extract();
        }
//        return given()
//                .log().all()
//                .filter(withCustomTemplates())
//
//                .when()
//                .get(url)
//                .then()
//                .statusCode(200)
//                .extract()
//                .path("automation_session.video_url");
//    }
}
