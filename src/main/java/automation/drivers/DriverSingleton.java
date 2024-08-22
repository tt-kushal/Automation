package automation.drivers;

import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class DriverSingleton {
    private static DriverSingleton instance = null;
    private static WebDriver driver;

    private DriverSingleton(String driver){
        instantiation(driver);
    }

    public static WebDriver instantiation (String strategy){

        DriverStrategy driverStrategy = DriverStrategyImplementors.chooseStrategy(strategy);
        driver = driverStrategy.setStrategy();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        if (driver == null) {
            Chrome chrome = new Chrome();
            driver = chrome.setStrategy();
        }

        return driver;

    }

    public static DriverSingleton getInstance(String driver){
        if (instance == null){
            instance = new DriverSingleton(driver);
        };

        return instance;
    }

    public void closeMethod(){
        instance = null;
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
