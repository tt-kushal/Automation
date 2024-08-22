import automation.drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import automation.pages.Checkout;
import automation.pages.Home;
import automation.pages.SignInPage;
import automation.utility.ConstantsKey;
import automation.utility.FrameworkProperties;

public class Main {
    public static void main(String[] args) {
        FrameworkProperties frameworkProperties = new FrameworkProperties();
       DriverSingleton driverSingleton = DriverSingleton.getInstance(frameworkProperties.getProperty(ConstantsKey.BROWSER));
       WebDriver driver = DriverSingleton.getDriver();
       driver.get(ConstantsKey.URL);

//        OnlyChromeDriver onlyChromeDriver = OnlyChromeDriver.getInstance();
//        WebDriver driver = OnlyChromeDriver.getDriver();
//        driver.get(ConstantsKey.URL);

        Checkout checkout = new Checkout();
        SignInPage login = new SignInPage();
        Home home = new Home();

        home.clickSignIn();
        login.loginAtInitial(frameworkProperties.getProperty(ConstantsKey.Email), frameworkProperties.getProperty(ConstantsKey.PASSWORD) );
        home.addFirstElement();
        home.addSecondElement();
        checkout.cartPageConfirmation2();

        checkout.checkOutProcess();
        checkout.addressTitle().contains(ConstantsKey.TITLE_CHECK);
        checkout.clickPlaceOrder();
        checkout.enterCardDetails(ConstantsKey.NAME, ConstantsKey.CARD_NUMBER, ConstantsKey.CVV_NUMBER,
                ConstantsKey.EXP_MONTH,ConstantsKey.EXP_YEAR);
        checkout.checkFinalConfirmation(ConstantsKey.ORDER_CONFIRMATION_MESSAGE);

        driver.quit();




    }
}
