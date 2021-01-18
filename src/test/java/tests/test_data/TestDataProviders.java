package tests.test_data;

import org.testng.annotations.DataProvider;

public class TestDataProviders implements TestConstants{

    @DataProvider(name = "InvalidTestDataFotLogin")
    public  static Object[][] testDataForLogin() {
        return new Object[][]{
                {"", PASSWORD, "Epic sadface: Username is required"},
                {USERNAME, "", "Epic sadface: Password is required"},
                {WRONG_USERNAME, WRONG_PASSWORD, "Epic sadface: Username and password do not match any user in this service"},
                {"", "", "Epic sadface: Username is required"}
        };
    }
}
