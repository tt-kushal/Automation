package automation.drivers;

import org.openqa.selenium.WebDriver;

public class OnlyChromeDriver {
    private static OnlyChromeDriver instance = null;
    private static WebDriver driver;

    private OnlyChromeDriver() {
        instanciation();
    }

    public static WebDriver instanciation() {
        if (driver == null) {
            Chrome chrome = new Chrome();
            driver = chrome.setStrategy();
        }
        return driver;
    }

    public static OnlyChromeDriver getInstance() {
        if (instance == null) {
            instance = new OnlyChromeDriver();
        }
        return instance;
    }

    public void closeMethod() {
            driver.quit();
            instance = null;
    }


    public static WebDriver getDriver() {
        return driver;
    }
}
