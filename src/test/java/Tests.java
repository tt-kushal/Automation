import automation.drivers.DriverSingleton;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import automation.pages.Checkout;
import automation.pages.Home;
import automation.pages.SignInPage;
import automation.utility.ConstantsKey;
import automation.utility.FrameworkProperties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class Tests {  //junit test cases

   static DriverSingleton driverSingleton;
   static WebDriver driver;
   static Checkout checkout;
   static Home home;
   static SignInPage signInPage;
   static FrameworkProperties frameworkProperties;

   @BeforeClass
   public static void initializeObject(){
      frameworkProperties = new FrameworkProperties();
     DriverSingleton.getInstance(frameworkProperties.getProperty(ConstantsKey.BROWSER));
     driver = DriverSingleton.getDriver();
     checkout = new Checkout();
     home = new Home();
     signInPage = new SignInPage();
   }
   @Test
   public void test01AuthenticatePage(){
      driver.get(ConstantsKey.URL);
      home.clickSignIn();
      signInPage.loginAtInitial(frameworkProperties.getProperty(ConstantsKey.Email),frameworkProperties.getProperty(ConstantsKey.PASSWORD));
      home.getUserName();
      assertEquals(frameworkProperties.getProperty(ConstantsKey.USERNAME), home.getUserName());
   }

   @Test
   public void test02AddProductsToCart(){
      driver.get(ConstantsKey.URL);
      home.addFirstElement();
      home.addSecondElement();
      assertEquals(ConstantsKey.CART_CONFIRMATION, checkout.cartPageConfirmation());
   }

   @Test
   public void test03TestingFullBuyingProcess(){
      driver.get(ConstantsKey.URL);
      checkout.checkOutProcess();
      checkout.clickPlaceOrder();
      checkout.enterCardDetails(ConstantsKey.NAME, ConstantsKey.CARD_NUMBER, ConstantsKey.CVV_NUMBER,
              ConstantsKey.EXP_MONTH,ConstantsKey.EXP_YEAR);
//      assertEquals(true,checkout.checkFinalConfirmation(ConstantsKey.ORDER_CONFIRMATION_MESSAGE));
      assertTrue(checkout.checkFinalConfirmation(ConstantsKey.ORDER_CONFIRMATION_MESSAGE));
   }
   




   @AfterClass
   public static void closeObjects(){
      driver.quit();
   }


}
