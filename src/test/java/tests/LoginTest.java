package tests;

import org.testng.annotations.Test;
import tests.test_data.TestDataProviders;
import utils.Retry;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pages.ProductPage.PRODUCT_LABEL_LOCATOR;

public class LoginTest extends BaseTest {

    @Test(description = "Entering valid credentials to login", retryAnalyzer = Retry.class)
    public void isSuccessfulLogin() {
        loginPageFactory.openPage()
                .waitForPageOpened()
                .login(USERNAME, PASSWORD)
                // .login(System.getenv().getOrDefault("username", PropertyReader.getProperty("username")), System.getenv().getOrDefault("password", PropertyReader.getProperty("password")))
                .waitForPageOpened();
        assertTrue(productPage.isPageOpened(), "Product page is not opened. Locator is not found: " + PRODUCT_LABEL_LOCATOR);
    }

    @Test(description = "Error message should appear when logging with invalid credentials", dataProvider = "InvalidTestDataFotLogin", dataProviderClass = TestDataProviders.class)
    public void errorMessageShouldAppearWhenLogging(String username, String password, String errorMessage) {
        loginPageFactory.openPage()
                .waitForPageOpened()
                .attemptToLogin(username, password);
        String actualErrorMessage = loginPageFactory.getErrorMessage();
        assertEquals(actualErrorMessage, errorMessage, "Invalid error message is displayed: " + actualErrorMessage);
    }
}
