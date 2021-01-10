package pages;

import constants.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract class BasePage implements Constants {
    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
    }

    public void openPage(String url) {
        driver.get(url);
    }

    public abstract void waitForPageOpened();
}
