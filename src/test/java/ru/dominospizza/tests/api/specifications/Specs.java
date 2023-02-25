package ru.dominospizza.tests.api.specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;
import ru.dominospizza.config.ApiConfig;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static ru.dominospizza.helpers.CustomApiListener.withCustomTemplates;

public class Specs {
    ApiConfig config = ConfigFactory.create(ApiConfig.class);
    public RequestSpecification requestSpec = with()
            .log().uri()
            .log().headers()
            .log().body()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .baseUri(config.apiBaseUrl())
            .basePath("/api");

    public ResponseSpecification responseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .log(STATUS)
                .log(BODY)
                .expectStatusCode(statusCode)
                .build();
    }
}
