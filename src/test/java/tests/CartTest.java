package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {

    @Test(description = "Returning to Products page from Shopping cart to continue shopping")
    public void returnToProductsPageWhenClickingContinueShoppingBtn() {
        cartPage.openPage();
        cartPage.waitForPageOpened();
        cartPage.clickContinueShoppingBtn();
        productPage.waitForPageOpened();
    }

    @Test(description = "Item should be removed from the shopping cartffffff")
    public void itemShouldBeRemovedFromCart() {
        productPage.openPage();
        productPage.waitForPageOpened();
        productPage.addProductToCart(SAUCE_LABS_BACKPACK_ITEM_NAME);
        cartPage.openPage();
        cartPage.waitForPageOpened();
        cartPage.removeItemFromCart(SAUCE_LABS_BACKPACK_ITEM_NAME);
        assertTrue(cartPage.cartIsEmpty(), "Cart is not empty");
    }

    @Test(description = "Item page should be opened by tapping on the product name")
    public void itemPageShouldBeOpenedByClickingOnItemName() {
        productPage.openPage();
        productPage.waitForPageOpened();
        productPage.addProductToCart(SAUCE_LABS_FLEECE_JACKET_ITEM_NAME);
        cartPage.openPage();
        cartPage.waitForPageOpened();
        cartPage.openItemPageFromCart(SAUCE_LABS_FLEECE_JACKET_ITEM_NAME);
        itemPage.waitForPageOpened();
        String actualItemPrice = itemPage.getItemPrice();
        String actualItemName = itemPage.getItemName();
        assertEquals(actualItemName, SAUCE_LABS_FLEECE_JACKET_ITEM_NAME, "Wrong item page is opened: " + SAUCE_LABS_FLEECE_JACKET_ITEM_NAME);
        assertEquals(actualItemPrice, "$" + SAUCE_LABS_FLEECE_JACKET_ITEM_PRICE);
        assertTrue(itemPage.removeBtnIsDisplayed(), "Remove button is not displayed");
    }

    @Test(description = "Checkout info page should be opened")
    public void checkoutInfoPageShouldBeOpened() {
        productPage.openPage();
        productPage.waitForPageOpened();
        productPage.addProductToCart(SAUCE_LABS_FLEECE_JACKET_ITEM_NAME);
        cartPage.openPage();
        cartPage.waitForPageOpened();
        cartPage.clickCheckoutBtn();
        checkoutInfoPage.waitForPageOpened();
    }
}
