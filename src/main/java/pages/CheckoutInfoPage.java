package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.fail;

@Log4j2
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

    @Override
    @Step("Open Checkout info page")
    public CheckoutInfoPage openPage() {
        openPage(BASE_URL + CHECKOUT_INFO_ENDPOINT);
        return this;
    }

    @Override
    public CheckoutInfoPage waitForPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(CONTINUE_BTN_LOCATOR));
        } catch (TimeoutException r) {
            fail("Cart page is not loaded. Locator " + CONTINUE_BTN_LOCATOR + " is not found");
        }
        return this;
    }

    public boolean isPageOpened() {
        if (driver.findElement(CONTINUE_BTN_LOCATOR).isDisplayed()) {
            return true;
        } else return false;
    }

    @Step("Click continue shopping button")
    public CheckoutOverviewPage continueCheckout() {
        log.info("continue checkout");
        driver.findElement(CONTINUE_BTN_LOCATOR).click();
        return new CheckoutOverviewPage(driver);
    }

    @Step("Cancel checkout")
    public ProductPage cancelCheckout() {
        log.info("Cancel checkout");
        driver.findElement(CANCEL_BTN_LOCATOR).click();
        return new ProductPage(driver);
    }

    public CheckoutInfoPage inputFirstName(String firstName) {
        driver.findElement(FIRSTNAME_INPUT_LOCATOR).sendKeys(firstName);
        return this;
    }

    public CheckoutInfoPage inputLastName(String lastName) {
        driver.findElement(LASTNAME_INPUT_LOCATOR).sendKeys(lastName);
        return this;
    }

    public CheckoutInfoPage inputZipCode(String zipCode) {
        driver.findElement(ZIPCODE_INPUT_LOCATOR).sendKeys(zipCode);
        return this;
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE_LOCATOR).getText();
    }

    public CheckoutInfoPage attemptToCheckout(String firstName, String lastName, String zipCode) {
        log.info("Input firstname: " + firstName + " / lastname: " + lastName + " / zipcode: " + zipCode);
        inputFirstName(firstName);
        inputLastName(lastName);
        inputZipCode(zipCode);
        continueCheckout();
        return this;
    }

    @Step("Make checkout")
    public CheckoutOverviewPage checkout(String firstName, String lastName, String zipCode) {
        attemptToCheckout(firstName, lastName, zipCode);
        return new CheckoutOverviewPage(driver);
    }
}
