package tests.selenide;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.selenide.pages.MainPage;

import static com.codeborne.selenide.Selenide.open;

@Owner("f27")
@Tag("selenide")
@Feature("Главная страница")
@DisplayName("Тесты с Selenide")
public class MainTests extends SelenideBase {

    @Test
    @Story("Проверяем элементы страницы")
    @DisplayName("Проверка тайтла страницы")
    void firstTest() {
        open("/"+System.getProperty("web.remote.driver.user"), MainPage.class)
                .checkTitle(testDataConfig.pagesMainTitle());
    }
}
