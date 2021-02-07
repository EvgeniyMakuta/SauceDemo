package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.fail;

@Log4j2
public class ProductPage extends BasePage {
    public static final String COMMON_PATH_FOR_ADD_REMOVE_BUTTON = "//*[contains(text(),'%s')]/ancestor::*[@class='inventory_item']";
    public static final String ADD_TO_CART_BUTTON_LOCATOR = COMMON_PATH_FOR_ADD_REMOVE_BUTTON + "//button[contains(@class, 'btn_primary')]";
    public static final String REMOVE_BUTTON_LOCATOR = COMMON_PATH_FOR_ADD_REMOVE_BUTTON + "//button[contains(@class, 'btn_secondary')]";
    public static final By CART_ICON_LOCATOR = By.cssSelector("[data-icon=shopping-cart]");
    public static final String PRODUCT_NAME_LOCATOR = "//*[@id='inventory_container']//*[contains(text(), '%s')]";
    public static final By SHOPPING_CART_COUNTER = By.xpath("//*[contains(@class, 'shopping_cart_badge')]");
    public static final By PRODUCT_LABEL_LOCATOR = By.cssSelector(".product_label");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Open Product page")
    public ProductPage openPage() {
        openPage(BASE_URL + PRODUCTS_ENDPOINT);
        return this;
    }

    @Override
    public ProductPage waitForPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_LABEL_LOCATOR));
        } catch (TimeoutException e) {
            log.error(String.format("Product page is not opened. Locator %s in not found", PRODUCT_LABEL_LOCATOR));
            fail("Product page is not loaded. Locator " + PRODUCT_LABEL_LOCATOR + " not found");
        }
        return this;
    }

    public boolean isPageOpened() {
        if (driver.findElement(PRODUCT_LABEL_LOCATOR).isDisplayed()) {
            return true;
        } else return false;
    }

    @Step("Add Item to shopping cart")
    public ProductPage addProductToCart(String productName) {
        log.info(String.format("Adding product %s to cart with locator: %s", productName, ADD_TO_CART_BUTTON_LOCATOR));
        driver.findElement(By.xpath(String.format(ADD_TO_CART_BUTTON_LOCATOR, productName))).click();
        return this;
    }

    @Step("Remove Item from shopping cart")
    public ProductPage removeProductFromCart(String productName) {
        log.info(String.format("Removing product %s from cart. Locator: %s", productName, REMOVE_BUTTON_LOCATOR));
        driver.findElement(By.xpath(String.format(REMOVE_BUTTON_LOCATOR, productName))).click();
        return this;
    }

    @Step("Click shopping cart icon")
    public CartPage clickShoppingCartIcon() {
        log.info(String.format("Clicking shopping cart icon with locator: %s", CART_ICON_LOCATOR));
        driver.findElement(CART_ICON_LOCATOR).click();
        return new CartPage(driver);
    }

    @Step("Open item page")
    public ItemPage openItemPage(String productName) {
        log.debug(String.format("Opening item page for %s with locator: %s", productName, PRODUCT_NAME_LOCATOR));
        driver.findElement(By.xpath(String.format(PRODUCT_NAME_LOCATOR, productName))).click();
        return new ItemPage(driver);
    }

    public String getShoppingCartNumberFromCounter() {
        log.debug(String.format("Getting shopping cart counter fron counter with locator: %s", SHOPPING_CART_COUNTER));
        String actualCartCounter = driver.findElement(SHOPPING_CART_COUNTER).getText();
        log.debug(String.format("Cart number from counter is %s", actualCartCounter));
        return actualCartCounter;
    }

    public boolean isAddToCartBtnNameDisplayed(String productName) {
        String actualButtonName = driver.findElement(By.xpath(String.format(ADD_TO_CART_BUTTON_LOCATOR, productName))).getText();
        return actualButtonName.contains(ADD_TO_CART_BUTTON_NAME);
    }

    public boolean isRemoveBtnNameDisplayed(String productName) {
        String actualButtonName = driver.findElement(By.xpath(String.format(REMOVE_BUTTON_LOCATOR, productName))).getText();
        return actualButtonName.contains(REMOVE_BUTTON_NAME);
    }
}
