package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pages.ProductPage.PRODUCT_LABEL_LOCATOR;

public class ItemTest extends BaseTest {

    @Test(description = "Checking that remove button is displayed after adding it to cart in Product page")
    public void removeBtnDisplayedWhenAddingItemInProductsPage() {
        productPage.openPage()
                .waitForPageOpened()
                .addProductToCart(SAUCE_LABS_BACKPACK_ITEM_NAME)
                .openItemPage(SAUCE_LABS_BACKPACK_ITEM_NAME);
        assertTrue(itemPage.isRemoveBtnDisplayed(), "Remove button is not displayed");
    }

    @Test(description = "Item should be added to the shopping cart")
    public void productShouldBeAddedIntoCart() {
        itemPage.openPage()
                .waitForPageOpened()
                .addItemToCart();
        String actualCartCounter = productPage.getShoppingCartNumberFromCounter();
        assertEquals(actualCartCounter, "1", "Wrong number in the cart counter: " + actualCartCounter);
        assertTrue(itemPage.isRemoveBtnDisplayed(), "Remove button is not displayed");
    }

    @Test(description = "Item should be removed from shopping cart in the Item page")
    public void productShouldBeRemovedFromCart() {
        itemPage.openPage()
                .waitForPageOpened()
                .addItemToCart()
                .removeItemFromCart();
        assertTrue(itemPage.isAddToCartBtnDisplayed(), "Add To Cart button is not displayed");
    }

    @Test(description = "Products page should be opened after clicking back btn")
    public void returnToProductsPage() {
        productPage.openPage()
                .waitForPageOpened()
                .openItemPage(SAUCE_LABS_BACKPACK_ITEM_NAME)
                .clickBackButton()
                .waitForPageOpened();
        assertTrue(productPage.isPageOpened(), "Item page is not opened. Locator is not found: " + PRODUCT_LABEL_LOCATOR);
    }
}
