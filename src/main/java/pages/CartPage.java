package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.testng.Assert.fail;

@Log4j2
public class CartPage extends BasePage {
    public static final By CHECKOUT_BTN_LOCATOR = By.cssSelector(".checkout_button");
    public static final By CONTINUE_SHOPPING_BTN_LOCATOR = By.xpath("//*[@class='btn_secondary'][text()='Continue Shopping']");
    public static final String COMMON_PATH_CART_ITEM = "//*[contains(text(),'%s')]/ancestor::*[@class='cart_item']";
    public static final String ITEM_PRICE_LOCATOR = COMMON_PATH_CART_ITEM + "//*[@class='inventory_item_price']";
    public static final String ITEM_QUANTITY_LOCATOR = COMMON_PATH_CART_ITEM + "//*[@class='cart_quantity']";
    public static final String REMOVE_BTN_LOCATOR = COMMON_PATH_CART_ITEM + "//button[contains(@class, cart_button)]";
    public static final String ITEM_NAME_LOCATOR = "//*[@class='cart_list']/descendant::*[contains(text(),'%s')]";
    public static final By ITEMS_LIST_IN_CART = By.cssSelector(".cart_item_label");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open Cart page")
    public CartPage openPage() {
        openPage(BASE_URL + CART_ENDPOINT);
        return this;
    }

    @Override
    public CartPage waitForPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_BTN_LOCATOR));
        } catch (TimeoutException e) {
            log.error(String.format("Cart page is not opened. Locator %s in not found", CHECKOUT_BTN_LOCATOR));
            fail("Cart page is not loaded. Locator " + CHECKOUT_BTN_LOCATOR + " is not found");
        }
        return this;
    }

    @Step("Click checkout button to checkout")
    public CheckoutInfoPage clickCheckoutBtn() {
        log.info(String.format("Clicking 'checkout' button. Locator: %s", CHECKOUT_BTN_LOCATOR));
        driver.findElement(CHECKOUT_BTN_LOCATOR).click();
        return new CheckoutInfoPage(driver);
    }

    @Step("Click continue button to continue shopping")
    public ProductPage clickContinueShoppingBtn() {
        log.info(String.format("Clicking 'continue shopping' button. Locator: %s", CONTINUE_SHOPPING_BTN_LOCATOR));
        driver.findElement(CONTINUE_SHOPPING_BTN_LOCATOR).click();
        return new ProductPage(driver);
    }

    @Step("Remove Item from cart")
    public CartPage removeItemFromCart(String productName) {
        log.info(String.format("Removing product '%s' from cart. Locator: %s", productName, REMOVE_BTN_LOCATOR));
        driver.findElement(By.xpath(String.format(REMOVE_BTN_LOCATOR, productName))).click();
        return this;
    }

    public String getItemPrice(String productName) {
        log.debug(String.format("Getting price for product %s with locator %s", productName, ITEM_PRICE_LOCATOR));
        String productPrice = driver.findElement(By.xpath(String.format(ITEM_PRICE_LOCATOR, productName))).getText();
        log.debug(String.format("Getting price for product %s with price: %s", productName, productPrice));
        return productPrice;
    }

    public String getItemQuantity(String productName) {
        log.debug(String.format("Getting quantity for product %s with locator %s", productName, ITEM_QUANTITY_LOCATOR));
        String productQuantity = driver.findElement(By.xpath(String.format(ITEM_QUANTITY_LOCATOR, productName))).getText();
        log.debug(String.format("Getting quantity for product %s with quantity: %s", productName, productQuantity));
        return productQuantity;
    }

    public boolean isItemAddedToCart(String productName, String productQuantity, String productPrice) {
        boolean result = getItemQuantity(productName).equals(productQuantity);
        result &= getItemPrice(productName).equals(productPrice);
        return result;
    }

    @Step("Open item from shopping cart")
    public ItemPage openItemPageFromCart(String productName) {
        log.debug(String.format("Opening page for product '%s' with locator '%s'", productName, ITEM_NAME_LOCATOR));
        driver.findElement(By.xpath(String.format(ITEM_NAME_LOCATOR, productName))).click();
        return new ItemPage(driver);
    }

    public int getNumberOfItemsInCart() {
        List<WebElement> itemsList = driver.findElements(ITEMS_LIST_IN_CART);
        log.debug(String.format("Number of items in the cart: %s", itemsList.size()));
        return itemsList.size();
    }

    public boolean isItemExistInCart(String productName) {
        List<WebElement> listOfItems = driver.findElements(ITEMS_LIST_IN_CART);
        log.debug(String.format("Number of items in the cart: '%s'. Looking for item: '%s'", listOfItems.size(), productName));
        for (WebElement element : listOfItems) {
            if (element.getText().contains(productName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCartEmpty() {
        List<WebElement> listOfItems = driver.findElements(ITEMS_LIST_IN_CART);
        if (listOfItems.isEmpty()) {
            return true;
        }
        return false;
    }
}
