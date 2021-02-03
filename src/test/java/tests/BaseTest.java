package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.*;
import steps.ProductStep;
import tests.test_data.TestConstants;
import utils.CapabilitiesGenerator;
import utils.TestListener;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

@Listeners(TestListener.class)
public class BaseTest implements TestConstants {
    WebDriver driver;
    ProductPage productPage;
    CartPage cartPage;
    ItemPage itemPage;
    CheckoutInfoPage checkoutInfoPage;
    CheckoutOverviewPage checkoutOverviewPage;
    LoginPageFactory loginPageFactory;
    protected ProductStep productStep;
    private Logger log;

    @BeforeMethod(description = "Setting up before test")
    public void setUp(ITestContext context) {
        WebDriverManager.chromedriver().setup();
        try {
            driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        } catch (SessionNotCreatedException e) {
            fail("Browser is not opened");
            log.fatal(e.getLocalizedMessage());
        }
        productStep = new ProductStep(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        createInstances();
        String variable = "driver";
        System.out.println("Setting driver into context with variable name " + variable);
        context.setAttribute(variable, driver);
    }

    @AfterMethod(alwaysRun = true, description = "Closing browser")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void createInstances() {
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        itemPage = new ItemPage(driver);
        checkoutInfoPage = new CheckoutInfoPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        loginPageFactory = new LoginPageFactory(driver);
    }
}
