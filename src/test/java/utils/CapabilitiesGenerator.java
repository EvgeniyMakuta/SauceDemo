package utils;

import org.openqa.selenium.chrome.ChromeOptions;

public class CapabilitiesGenerator {

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

//        if (System.getProperty("headless").equals(true)) {
//            options.addArguments("--headless");
//        }
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        options.addArguments("-incognito");
        options.setAcceptInsecureCerts(true);
        options.addArguments("--disable-extensions");
        return options;
    }
}
