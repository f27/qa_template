package tests.api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static api.specs.RequestSpec.spec;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiTests extends ApiBase {
    @Test
    void firstTest() {
        String response = given(spec)
                .get()
                .then()
                .statusCode(200)
                .extract().asPrettyString();

        assertThat(response).containsPattern("<title.*?>\\s*?" + testDataConfig.pagesMainTitle() + "\\s*?</title>");
    }
}
