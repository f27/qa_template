package tests.selenide;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import tests.TestData;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static helpers.AttachmentsHelper.*;
import static helpers.DriverHelper.*;
import static helpers.DriverHelper.isVideoOn;

public class TestBase extends TestData {


    @BeforeEach
    void setup() {
        configureDriver(testDataConfig.baseUrl());
    }

    @AfterEach
    void addAttachments() {


        if(!driverExists()) return;

        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        if (isVideoOn()) attachVideo(getSessionId());

        closeWebDriver();
    }

}
