package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static tests.base.Constants.*;

public class CartTest extends BaseTest {

    @Test(description = "Returning to Products page from Shopping cart to continue shopping")
    public void returnToProductsPageWhenClickingContinueShoppingBtn() {
        cartPage.openPage();
        cartPage.isPageOpened();
        cartPage.clickContinueShoppingBtn();
        productPage.isPageOpened();
    }

    @Test(description = "Item should be removed from the shopping cartffffff")
    public void itemShouldBeRemovedFromCart() {
        productPage.openPage();
        productPage.isPageOpened();
        productPage.addProductToCart(SAUCE_LABS_BACKPACK_ITEM_NAME);
        cartPage.openPage();
        cartPage.isPageOpened();
        cartPage.removeItemFromCart(SAUCE_LABS_BACKPACK_ITEM_NAME);

        assertTrue(cartPage.cartIsEmpty(), "Cart is not empty");
    }

    @Test(description = "Item page should be opened by tapping on the product name")
    public void itemPageShouldBeOpenedByClickingOnItemName() {
        productPage.openPage();
        productPage.isPageOpened();
        productPage.addProductToCart(SAUCE_LABS_FLEECE_JACKET_ITEM_NAME);
        cartPage.openPage();
        cartPage.isPageOpened();
        cartPage.openItemPageFromCart(SAUCE_LABS_FLEECE_JACKET_ITEM_NAME);
        itemPage.isPageOpened();
        String actualItemPrice = itemPage.getItemPrice();
        String actualItemName = itemPage.getItemName();

        assertEquals(actualItemName, SAUCE_LABS_FLEECE_JACKET_ITEM_NAME, "Wrong item page is opened: " + SAUCE_LABS_FLEECE_JACKET_ITEM_NAME);
        assertEquals(actualItemPrice, "$" + SAUCE_LABS_FLEECE_JACKET_ITEM_PRICE);
        assertTrue(itemPage.removeBtnIsDisplayed(), "Remove button is not displayed");
    }

    @Test(description = "Checkout info page should be opened")
    public void checkoutInfoPageShouldBeOpened() {
        productPage.openPage();
        productPage.isPageOpened();
        productPage.addProductToCart(SAUCE_LABS_FLEECE_JACKET_ITEM_NAME);
        cartPage.openPage();
        cartPage.isPageOpened();
        cartPage.clickCheckoutBtn();
        checkoutInfoPage.isPageOpened();
    }
}
