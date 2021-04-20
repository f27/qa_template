package tests.selenide.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPage {
    @Step("Открываем главную страницу")
    public MainPage openPage() {
        open("/");
        return this;
    }

    @Step("Сравниваем тайтл")
    public void checkTitle(String title) {
        assertThat(title()).isEqualTo(title);
    }
}
