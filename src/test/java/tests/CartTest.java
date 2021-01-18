package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pages.CheckoutInfoPage.CONTINUE_BTN_LOCATOR;
import static pages.ProductPage.PRODUCT_LABEL_LOCATOR;

public class CartTest extends BaseTest {

    @Test(description = "Returning to Products page from Shopping cart to continue shopping")
    public void returnToProductsPageWhenClickingContinueShoppingBtn() {
        cartPage.openPage()
                .waitForPageOpened()
                .clickContinueShoppingBtn()
                .waitForPageOpened();
        assertTrue(productPage.isPageOpened(), "Product page is not opened. Locator is not found: " + PRODUCT_LABEL_LOCATOR);
    }

    @Test(description = "Item should be removed from the shopping cart")
    public void itemShouldBeRemovedFromCart() {
        productPage.openPage()
                .waitForPageOpened()
                .addProductToCart(SAUCE_LABS_BACKPACK_ITEM_NAME);
        cartPage.openPage()
                .waitForPageOpened()
                .removeItemFromCart(SAUCE_LABS_BACKPACK_ITEM_NAME);
        assertTrue(cartPage.isCartEmpty(), "Cart is not empty");
    }

    @Test(description = "Item page should be opened by tapping on the product name")
    public void itemPageShouldBeOpenedByClickingOnItemName() {
        productPage.openPage()
                .waitForPageOpened()
                .addProductToCart(SAUCE_LABS_FLEECE_JACKET_ITEM_NAME);
        cartPage.openPage()
                .waitForPageOpened()
                .openItemPageFromCart(SAUCE_LABS_FLEECE_JACKET_ITEM_NAME)
                .waitForPageOpened();
        String actualItemPrice = itemPage.getItemPrice();
        String actualItemName = itemPage.getItemName();
        assertEquals(actualItemName, SAUCE_LABS_FLEECE_JACKET_ITEM_NAME, "Wrong item page is opened: " + SAUCE_LABS_FLEECE_JACKET_ITEM_NAME);
        assertEquals(actualItemPrice, "$" + SAUCE_LABS_FLEECE_JACKET_ITEM_PRICE);
        assertTrue(itemPage.isRemoveBtnDisplayed(), "Remove button is not displayed");
    }

    @Test(description = "Checkout info page should be opened")
    public void checkoutInfoPageShouldBeOpened() {
        productPage.openPage()
                .waitForPageOpened()
                .addProductToCart(SAUCE_LABS_FLEECE_JACKET_ITEM_NAME);
        cartPage.openPage()
                .waitForPageOpened()
                .clickCheckoutBtn()
                .waitForPageOpened();
        assertTrue(checkoutInfoPage.isPageOpened(), "CheckoutInfoPage is not opened. Locator is not found: " + CONTINUE_BTN_LOCATOR);
    }
}
