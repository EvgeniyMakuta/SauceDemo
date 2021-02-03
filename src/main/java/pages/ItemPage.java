package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.fail;

public class ItemPage extends BasePage {
    public static final By BACK_BUTTON_LOCATOR = By.cssSelector(".inventory_details_back_button");
    public static final By ITEM_NAME_LOCATOR = By.cssSelector(".inventory_details_name");
    public static final By ADD_TO_CART_BUTTON_LOCATOR = By.xpath("//button[contains(@class, 'btn_primary')]");
    public static final By REMOVE_BUTTON_LOCATOR = By.xpath("//button[contains(@class, 'btn_secondary')]");
    public static final By ITEM_PRICE_LOCATOR = By.cssSelector(".inventory_details_price");

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Open Item page")
    public ItemPage openPage() {
        openPage(BASE_URL + SAUCE_LABS_BACKPACK_ITEM_ENDPOINT);
        return this;
    }

    @Override
    public ItemPage waitForPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(BACK_BUTTON_LOCATOR));
        } catch (TimeoutException r) {
            fail("Item page " + getItemName() + " is not loaded");
        }
        return this;
    }

    public String getItemName() {
        return driver.findElement(ITEM_NAME_LOCATOR).getText();
    }

    @Step("Click back button to go back to Product page")
    public ProductPage clickBackButton() {
        driver.findElement(BACK_BUTTON_LOCATOR).click();
        return new ProductPage(driver);
    }

    @Step("Add item to shopping cart")
    public ItemPage addItemToCart() {
        driver.findElement(ADD_TO_CART_BUTTON_LOCATOR).click();
        return this;
    }

    @Step("Remove item from shopping cart")
    public ItemPage removeItemFromCart() {
        driver.findElement(REMOVE_BUTTON_LOCATOR).click();
        return this;
    }

    public String getItemPrice() {
        return driver.findElement(ITEM_PRICE_LOCATOR).getText();
    }

    public boolean isAddToCartBtnDisplayed() {
        return driver.findElement(ADD_TO_CART_BUTTON_LOCATOR).isDisplayed();
    }

    public boolean isRemoveBtnDisplayed() {
        return driver.findElement(REMOVE_BUTTON_LOCATOR).isDisplayed();
    }

    public boolean isPageOpened() {
        return driver.findElement(BACK_BUTTON_LOCATOR).isDisplayed();
    }
}
