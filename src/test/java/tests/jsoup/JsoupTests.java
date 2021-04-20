package tests.jsoup;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.TestData.testDataConfig;
import static tests.jsoup.JsoupBase.baseUrl;

@Owner("f27")
@Tag("jsoup")
@Feature("Главная страница")
@DisplayName("Тесты с html парсером jsoup")
public class JsoupTests {

    @Test
    @Story("Проверяем тайтл главной страницы")
    void firstJsoupTest() {
        AtomicReference<String> title = new AtomicReference<>();

        step("Открываем главную страницу", () ->
                title.set(Jsoup.connect(baseUrl).get().title())
        );

        step("Сравниваем тайтл", () ->
                assertThat(title.get()).isEqualTo(testDataConfig.pagesMainTitle())
        );
    }
}
