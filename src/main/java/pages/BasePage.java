package pages;

import constants.Constants;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
abstract class BasePage implements Constants {
    WebDriver driver;
    WebDriverWait wait;
    int implicitValue = 20;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, implicitValue);
        log.debug("Implicit Timeout = " + implicitValue);
        PageFactory.initElements(driver, this);
    }

    public BasePage openPage(String url) {
        log.info("Open url: " + url);
        driver.get(url);
        return this;
    }

    public abstract BasePage openPage();

    public abstract BasePage waitForPageOpened();
}
