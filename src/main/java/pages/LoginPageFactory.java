package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.fail;

public class LoginPageFactory extends BasePage {

    @FindBy(xpath = "//*[@data-test='username']")
    WebElement usernameInput;
    @FindBy(xpath = "//*[@data-test='password']")
    WebElement passwordInput;
    @FindBy(id = "login-button")
    WebElement loginBtn;
    @FindBy(css = "[data-test=error]")
    WebElement errorMsg;

    public LoginPageFactory(WebDriver driver) {
        super(driver);
    }

    public LoginPageFactory openPage() {
        openPage(BASE_URL);
        return this;
    }

    @Override
    public LoginPageFactory waitForPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginBtn));
        } catch (TimeoutException e) {
            fail("Page is not loaded. Not found locator: " + loginBtn);
        }
        return this;
    }

    public ProductPage login(String username, String password) {
        attemptToLogin(username, password);
        return new ProductPage(driver);
    }

    public LoginPageFactory attemptToLogin(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();
        return this;
    }

    public String getErrorMessage() {
        return errorMsg.getText();
    }
}
