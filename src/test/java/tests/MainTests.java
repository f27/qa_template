package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class MainTests extends TestBase{
    @Test
    void firstTest() {

        System.out.println(testDataConfig.baseUrl());
        open("/");
    }
}
