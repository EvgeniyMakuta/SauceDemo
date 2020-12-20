package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.testng.Assert.fail;
import static tests.base.Constants.BASE_URL;
import static tests.base.Constants.CART_ENDPOINT;

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

    @Override
    public void openPage() {
        driver.get(BASE_URL + CART_ENDPOINT);
    }

    @Override
    public void isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_BTN_LOCATOR));
        } catch (TimeoutException r) {
            fail("Cart page is not loaded. Locator " + CHECKOUT_BTN_LOCATOR + " is not found");
        }
    }

    public void clickCheckoutBtn() {
        driver.findElement(CHECKOUT_BTN_LOCATOR).click();
    }

    public void clickContinueShoppingBtn() {
        driver.findElement(CONTINUE_SHOPPING_BTN_LOCATOR).click();
    }

    public void removeItemFromCart(String productName) {
        driver.findElement(By.xpath(String.format(REMOVE_BTN_LOCATOR, productName))).click();
    }

    public String getItemPrice(String productName) {
        return driver.findElement(By.xpath(String.format(ITEM_PRICE_LOCATOR, productName))).getText();
    }

    public String getItemQuantity(String productName) {
        return driver.findElement(By.xpath(String.format(ITEM_QUANTITY_LOCATOR, productName))).getText();
    }

    public void openItemPageFromCart(String productName) {
        driver.findElement(By.xpath(String.format(ITEM_NAME_LOCATOR, productName))).click();
    }

    public int getNumberOfItemsInCart() {
        List<WebElement> itemsList = driver.findElements(ITEMS_LIST_IN_CART);
        return itemsList.size();
    }

    public boolean getItemNameFromList(String productName) {
        List<WebElement> listOfItems = driver.findElements(ITEMS_LIST_IN_CART);
        for (WebElement element : listOfItems) {
            if (element.getText().contains(productName)) {
                return true;
            }
        }
        return false;
    }

    public boolean cartIsEmpty() {
        List<WebElement> listOfItems = driver.findElements(ITEMS_LIST_IN_CART);
        if (listOfItems.isEmpty()) {
            return true;
        }
        return false;
    }
}
