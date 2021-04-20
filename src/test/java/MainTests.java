import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTests {
    @Test
    void firstTest() {
        assertThat(System.getProperty("selenoid.user")).isEqualTo("user1");

    }
}
