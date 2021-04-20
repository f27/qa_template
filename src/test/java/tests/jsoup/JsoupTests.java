package tests.jsoup;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static tests.TestData.testDataConfig;
import static tests.jsoup.JsoupBase.baseUrl;

public class JsoupTests {
    @Test
    void firstJsoupTest() throws IOException {
        String title = Jsoup.connect(baseUrl).get().title();

        assertThat(title).isEqualTo(testDataConfig.pagesMainTitle());
    }
}
