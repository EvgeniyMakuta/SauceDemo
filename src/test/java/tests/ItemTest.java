package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static tests.base.Constants.*;

public class ItemTest extends BaseTest {

    @Test(description = "Checking that remove button is displayed after adding it to cart in Product page")
    public void removeBtnDisplayedWhenAddingItemInProductsPage() {
        productPage.openPage();
        productPage.isPageOpened();
        productPage.addProductToCart(SAUCE_LABS_BACKPACK_ITEM_NAME);
        productPage.openItemPage(SAUCE_LABS_BACKPACK_ITEM_NAME);

        assertTrue(itemPage.removeBtnIsDisplayed(), "Remove button is not displayed");
    }

    @Test(description = "Item should be added to the shopping cart")
    public void productShouldBeAddedIntoCart() {
        itemPage.openPage();
        itemPage.isPageOpened();
        itemPage.addItemToCart();
        String actualCartCounter = productPage.getShoppingCartNumberFromCounter();

        assertEquals(actualCartCounter, "1", "Wrong number in the cart counter: " + actualCartCounter);
        assertTrue(itemPage.removeBtnIsDisplayed(), "Remove button is not displayed");
    }

    @Test(description = "Item should be removed from shopping cart in the Item page")
    public void productShouldBeRemovedFromCart() {
        itemPage.openPage();
        itemPage.isPageOpened();
        itemPage.addItemToCart();
        itemPage.removeItemFromCart();

        assertTrue(itemPage.addToCartBtnIsDisplayed(), "Add To Cart button is not displayed");
    }

    @Test(description = "Products page should be opened after clicking back btn")
    public void returnToProductsPage() {
        productPage.openPage();
        productPage.isPageOpened();
        productPage.openItemPage(SAUCE_LABS_BACKPACK_ITEM_NAME);
        itemPage.clickBackButton();
        productPage.isPageOpened();
    }
}
