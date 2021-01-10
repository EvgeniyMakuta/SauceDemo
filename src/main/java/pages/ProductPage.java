package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.fail;

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

    public void openPage() {
        openPage(BASE_URL + PRODUCTS_ENDPOINT);
    }

    @Override
    public void waitForPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_LABEL_LOCATOR));
        } catch (TimeoutException e) {
            fail("Product page is not loaded. Locator " + PRODUCT_LABEL_LOCATOR + " not found");
        }
    }

    public void addProductToCart(String productName) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_BUTTON_LOCATOR, productName))).click();
    }

    public void removeProductFromCart(String productName) {
        driver.findElement(By.xpath(String.format(REMOVE_BUTTON_LOCATOR, productName))).click();
    }

    public void clickShoppingCartIcon() {
        driver.findElement(CART_ICON_LOCATOR).click();
    }

    public void openItemPage(String productName) {
        driver.findElement(By.xpath(String.format(PRODUCT_NAME_LOCATOR, productName))).click();
    }

    public String getShoppingCartNumberFromCounter() {
        return driver.findElement(SHOPPING_CART_COUNTER).getText();
    }

    public boolean addToCartBtnNameIsDisplayed(String productName) {
        String actualButtonName = driver.findElement(By.xpath(String.format(ADD_TO_CART_BUTTON_LOCATOR, productName))).getText();
        if (actualButtonName.contains(ADD_TO_CART_BUTTON_NAME)) {
            return true;
        }
        return false;
    }

    public boolean removeBtnNameIsDisplayed(String productName) {
        String actualButtonName = driver.findElement(By.xpath(String.format(REMOVE_BUTTON_LOCATOR, productName))).getText();
        if (actualButtonName.contains(REMOVE_BUTTON_NAME)) {
            return true;
        }
        return false;
    }
}
