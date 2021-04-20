package tests.selenide;

import org.junit.jupiter.api.Test;
import tests.selenide.pages.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class MainTests extends TestBase {
    @Test
    void firstTest() {

        open("/", MainPage.class)
                .checkTitle(testDataConfig.pagesMainTitle());
    }
}
