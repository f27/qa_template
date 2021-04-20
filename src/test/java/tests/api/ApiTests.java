package tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static api.specs.RequestSpec.spec;
import static org.assertj.core.api.Assertions.assertThat;

@Owner("f27")
@Tag("api")
@Feature("Главная страница")
@DisplayName("Тесты с REST assured")
public class ApiTests extends ApiBase {

    @Test
    @Story("Проверяем тайтл главной страницы")
    void firstTest() {
        AtomicReference<String> response = new AtomicReference<>();

        step("Открываем главную страницу", () ->
                response.set(given(spec)
                        .get()
                        .then()
                        .statusCode(200)
                        .extract().asPrettyString())
        );

        step("Сравниваем тайтл", () ->
                assertThat(response.get()).containsPattern("<title.*?>\\s*?" + testDataConfig.pagesMainTitle() + "\\s*?</title>")
        );
    }
}
