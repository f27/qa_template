package tests.selenide.pages;

import static com.codeborne.selenide.Selenide.title;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPage {
    public void checkTitle(String title) {
        assertThat(title()).isEqualTo(title);
    }
}
