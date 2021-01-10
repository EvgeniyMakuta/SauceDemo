package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsTest extends BaseTest {

    @Test(description = "Products should be added into shopping cart")
    public void productsShouldBeAddedToCart() {
        productPage.openPage();
        productPage.waitForPageOpened();
        productPage.addProductToCart(SAUCE_LABS_BACKPACK_ITEM_NAME);
        productPage.addProductToCart(SAUCE_LABS_BOLT_T_SHIRT_ITEM_NAME);
        productPage.addProductToCart(SAUCE_LABS_FLEECE_JACKET_ITEM_NAME);
        cartPage.openPage();
        cartPage.waitForPageOpened();
        int actualNumberOfItemsInCart = cartPage.getNumberOfItemsInCart();
        assertTrue(cartPage.isItemAddedToCart(SAUCE_LABS_BACKPACK_ITEM_NAME, ITEM_QUANTITY, SAUCE_LABS_BACKPACK_ITEM_PRICE));
        assertEquals(actualNumberOfItemsInCart, 3, "Wrong number of items in the shopping cart: " + actualNumberOfItemsInCart);
    }

    @Test(description = "Products should be removed from shopping cart in Products page ")
    public void productsShouldBeRemovedFromCart() {
        productPage.openPage();
        productPage.waitForPageOpened();
        productPage.addProductToCart(SAUCE_LABS_BACKPACK_ITEM_NAME);
        productPage.addProductToCart(SAUCE_LABS_BOLT_T_SHIRT_ITEM_NAME);
        productPage.removeProductFromCart(SAUCE_LABS_BACKPACK_ITEM_NAME);
        productPage.clickShoppingCartIcon();
        int actualNumberOfItemsInCart = cartPage.getNumberOfItemsInCart();
        assertTrue(cartPage.getItemNameFromList(SAUCE_LABS_BOLT_T_SHIRT_ITEM_NAME));
        assertEquals(actualNumberOfItemsInCart, 1, "Wrong number of items in the shopping cart: " + actualNumberOfItemsInCart);
    }

    @Test(description = "Product count in shopping cart should be increased")
    public void productCountInShoppingCartShouldBeIncreased() {
        productPage.openPage();
        productPage.waitForPageOpened();
        productPage.addProductToCart(SAUCE_LABS_BACKPACK_ITEM_NAME);
        productPage.addProductToCart(SAUCE_LABS_BOLT_T_SHIRT_ITEM_NAME);
        String actualCartCounter = productPage.getShoppingCartNumberFromCounter();
        assertEquals(actualCartCounter, "2", "Wrong number in the cart counter: " + actualCartCounter);
    }

    @Test(description = "Product count in shopping cart should be decreased")
    public void productCountInShoppingCartShouldBeDecreased() {
        productPage.openPage();
        productPage.waitForPageOpened();
        productPage.addProductToCart(SAUCE_LABS_BACKPACK_ITEM_NAME);
        productPage.addProductToCart(SAUCE_LABS_BOLT_T_SHIRT_ITEM_NAME);
        productPage.removeProductFromCart(SAUCE_LABS_BOLT_T_SHIRT_ITEM_NAME);
        String actualCartCounter = productPage.getShoppingCartNumberFromCounter();
        assertEquals(actualCartCounter, "1", "Wrong number in the cart counter: " + actualCartCounter);
    }

    @Test(description = "Item page should be opened after clicking on product name")
    public void itemPageShouldBeOpenedByClickingOnItemName() {
        productPage.openPage();
        productPage.waitForPageOpened();
        productPage.openItemPage(SAUCE_LABS_BACKPACK_ITEM_NAME);
        itemPage.waitForPageOpened();
        String actualItemPrice = itemPage.getItemPrice();
        String actualItemName = itemPage.getItemName();
        assertEquals(actualItemName, SAUCE_LABS_BACKPACK_ITEM_NAME, "Wrong item page is opened: " + SAUCE_LABS_BACKPACK_ITEM_NAME);
        assertEquals(actualItemPrice, "$" + SAUCE_LABS_BACKPACK_ITEM_PRICE);
        assertTrue(itemPage.addToCartBtnIsDisplayed(), "Add To Cart button is not displayed");
    }

    @Test(description = "Button Add/Remove product should change name after adding/removing product")
    public void buttonToAddRemoveProductShouldChangeNameAfterAddingRemovingProduct() {
        productPage.openPage();
        productPage.waitForPageOpened();
        boolean actualAddRemoveBtnBeforeAddingItemToCart = productPage.addToCartBtnNameIsDisplayed(SAUCE_LABS_BACKPACK_ITEM_NAME);
        productPage.addProductToCart(SAUCE_LABS_BACKPACK_ITEM_NAME);
        boolean actualAddRemoveBtnAfterAddingItemToCart = productPage.removeBtnNameIsDisplayed(SAUCE_LABS_BACKPACK_ITEM_NAME);
        assertTrue(actualAddRemoveBtnBeforeAddingItemToCart);
        assertTrue(actualAddRemoveBtnAfterAddingItemToCart);
    }
}
