package tests.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import tests.TestData;

public class ApiBase extends TestData {
    @BeforeAll
    static void setupApi() {
        RestAssured.baseURI = testDataConfig.baseUrl();
    }
}
