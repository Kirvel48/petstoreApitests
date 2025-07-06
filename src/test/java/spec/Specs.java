package spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.RestAssured.with;

public class Specs {

    private static ResponseSpecification getBasicResponseSpec(int expectedStatusCode) {
        return new ResponseSpecBuilder()
                .log(ALL)
                .expectStatusCode(expectedStatusCode)
                .build();
    }

    public static ResponseSpecification responseSpec200 = getBasicResponseSpec(200);
    public static ResponseSpecification responseSpec404 = getBasicResponseSpec(404);

    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(JSON);
}