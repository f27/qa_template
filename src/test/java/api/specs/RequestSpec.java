package api.specs;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static api.LogFilter.filters;

public class RequestSpec {
    public static RequestSpecification spec = RestAssured
            .given()
            .log().uri()
            .filter(filters().withCustomTemplates())
            .contentType("application/x-www-form-urlencoded; charset=UTF-8");
}
