package tests;

import config.TestDataConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.AttachmentsHelper.*;
import static helpers.DriverHelper.*;
import static helpers.DriverHelper.isVideoOn;

public class TestBase {
    public static final TestDataConfig testDataConfig = ConfigFactory.create(TestDataConfig.class, System.getProperties());

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
