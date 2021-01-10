package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(description = "Entering valid credentials to login")
    public void isSuccessfulLogin() {
        loginPage.openPage();
        loginPage.waitForPageOpened();
        loginPage.login(USERNAME, PASSWORD);
        productPage.waitForPageOpened();
    }

    @Test(description = "Error message should appear when logging with invalid credentials", dataProvider = "InvalidTestDataFotLogin")
    public void errorMessageShouldAppearWhenLogging(String username, String password, String errorMessage) {
        loginPage.openPage();
        loginPage.waitForPageOpened();
        loginPage.attemptToLogin(username, password);
        String actualErrorMessage = loginPage.getErrorMessage();
        assertEquals(actualErrorMessage, errorMessage, "Invalid error message is displayed: " + actualErrorMessage);
    }

    @DataProvider(name = "InvalidTestDataFotLogin")
    public Object[][] testDataForLogin() {
        return new Object[][]{
                {"", PASSWORD, "Epic sadface: Username is required"},
                {USERNAME, "", "Epic sadface: Password is required"},
                {WRONG_USERNAME, WRONG_PASSWORD, "Epic sadface: Username and password do not match any user in this service"},
                {"", "", "Epic sadface: Username is required"}
        };
    }
}
