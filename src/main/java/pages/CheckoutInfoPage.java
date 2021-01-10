package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.fail;

public class CheckoutInfoPage extends BasePage {
    public static final By CONTINUE_BTN_LOCATOR = By.cssSelector("[type=submit]");
    public static final By CANCEL_BTN_LOCATOR = By.cssSelector(".cart_cancel_link");
    public static final By FIRSTNAME_INPUT_LOCATOR = By.id("first-name");
    public static final By ZIPCODE_INPUT_LOCATOR = By.id("postal-code");
    public static final By LASTNAME_INPUT_LOCATOR = By.id("last-name");
    public static final By ERROR_MESSAGE_LOCATOR = By.cssSelector("[data-test='error']");

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        openPage(BASE_URL + CHECKOUT_INFO_ENDPOINT);
    }

    @Override
    public void waitForPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(CONTINUE_BTN_LOCATOR));
        } catch (TimeoutException r) {
            fail("Cart page is not loaded. Locator " + CONTINUE_BTN_LOCATOR + " is not found");
        }
    }

    public void continueCheckout() {
        driver.findElement(CONTINUE_BTN_LOCATOR).click();
    }

    public void cancelCheckout() {
        driver.findElement(CANCEL_BTN_LOCATOR).click();
    }

    public void inputFirstName(String firstName) {
        driver.findElement(FIRSTNAME_INPUT_LOCATOR).sendKeys(firstName);
    }

    public void inputLastName(String lastName) {
        driver.findElement(LASTNAME_INPUT_LOCATOR).sendKeys(lastName);
    }

    public void inputZipCode(String zipCode) {
        driver.findElement(ZIPCODE_INPUT_LOCATOR).sendKeys(zipCode);
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE_LOCATOR).getText();
    }

    public void attemptToCheckout(String firstName, String lastName, String zipCode) {
        driver.findElement(FIRSTNAME_INPUT_LOCATOR).sendKeys(firstName);
        driver.findElement(LASTNAME_INPUT_LOCATOR).sendKeys(lastName);
        driver.findElement(ZIPCODE_INPUT_LOCATOR).sendKeys(zipCode);
        driver.findElement(CONTINUE_BTN_LOCATOR).click();
    }

    public void checkout(String firstName, String lastName, String zipCode) {
        attemptToCheckout(firstName, lastName, zipCode);
    }
}
