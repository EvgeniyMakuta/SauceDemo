package utils;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeOptions;

@Log4j2
public class CapabilitiesGenerator {

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

//        if (System.getProperty("headless").equals(true)) {
//            options.addArguments("--headless");
//        }
        log.debug(" Used browser: " + options.getBrowserName());
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        options.addArguments("-incognito");
        options.addArguments("--headless");
        options.setAcceptInsecureCerts(true);
        options.addArguments("--disable-extensions");
        return options;
    }
}
