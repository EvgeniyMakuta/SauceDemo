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

    @Override
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
            fail("Cart page is not loaded. Locator " + CHECKOUT_BTN_LOCATOR + " is not found");
        }
        return this;
    }

    @Step("Click checkout button to checkout")
    public CheckoutInfoPage clickCheckoutBtn() {
        driver.findElement(CHECKOUT_BTN_LOCATOR).click();
        return new CheckoutInfoPage(driver);
    }

    @Step("Click continue button to continue shopping")
    public ProductPage clickContinueShoppingBtn() {
        driver.findElement(CONTINUE_SHOPPING_BTN_LOCATOR).click();
        return new ProductPage(driver);
    }

    @Step("Remove Item from cart")
    public CartPage removeItemFromCart(String productName) {
        driver.findElement(By.xpath(String.format(REMOVE_BTN_LOCATOR, productName))).click();
        return this;
    }

    public String getItemPrice(String productName) {
        String productPrice = driver.findElement(By.xpath(String.format(ITEM_PRICE_LOCATOR, productName))).getText();
        log.info("Get  price for product: " + productName + " - " + "Actual Price: " + productPrice);
        return productPrice;
    }

    public String getItemQuantity(String productName) {
        String productQuantity = driver.findElement(By.xpath(String.format(ITEM_QUANTITY_LOCATOR, productName))).getText();
        log.info("Get quantity for product: " + productName + " - " + "Actual Price: " + productQuantity);
        return productQuantity;
    }

    public boolean isItemAddedToCart(String productName, String productQuantity, String productPrice) {
        boolean result = getItemQuantity(productName).equals(productQuantity);
        result &= getItemPrice(productName).equals(productPrice);
        return result;
    }

    @Step("Open item from shopping cart")
    public ItemPage openItemPageFromCart(String productName) {
        log.info("Open page for product: " + productName);
        driver.findElement(By.xpath(String.format(ITEM_NAME_LOCATOR, productName))).click();
        return new ItemPage(driver);
    }

    public int getNumberOfItemsInCart() {
        List<WebElement> itemsList = driver.findElements(ITEMS_LIST_IN_CART);
        return itemsList.size();
    }

    public boolean isItemExistInCart(String productName) {
        List<WebElement> listOfItems = driver.findElements(ITEMS_LIST_IN_CART);
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
