package tests;

import config.TestDataConfig;
import org.aeonbits.owner.ConfigFactory;

public class TestData {
    public static final TestDataConfig testDataConfig = ConfigFactory.create(TestDataConfig.class, System.getProperties());
}
