package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.ProductPage;

public class ProductStep {
    private ProductPage productPage;

    public ProductStep(WebDriver driver) {
        productPage = new ProductPage(driver);
    }

    @Step("Adding '{productName}' to cart ")
    public ProductStep addItemToCart(String productName) {
        productPage
                .openPage()
                .waitForPageOpened()
                .addProductToCart(productName);
        return this;
    }


}
