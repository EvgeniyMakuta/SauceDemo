package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.ProductPage;

@Log4j2
public class ProductStep {
    private ProductPage productPage;

    public ProductStep(WebDriver driver) {
        productPage = new ProductPage(driver);
    }

    @Step("Adding '{productName}' to cart")
    public ProductStep addItemToCart(String productName) {
        log.info(String.format("Adding product '%s' to shopping cart", productName));
        productPage
                .openPage()
                .waitForPageOpened()
                .addProductToCart(productName);
        return this;
    }
}
