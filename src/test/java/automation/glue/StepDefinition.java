package automation.glue;

import automation.config.AutomationFrameworkConfiguration;
import automation.drivers.DriverSingleton;
import automation.pages.Checkout;
import automation.utility.TestCases;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import automation.pages.Home;
import automation.pages.SignInPage;
import automation.utility.ConfiguringProperties;
import automation.utility.ConstantsKey;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//3
@CucumberContextConfiguration
@ContextConfiguration(classes = AutomationFrameworkConfiguration.class )
public class StepDefinition {
    private WebDriver driver;
    private Home home;
    private SignInPage signInPage;
    private Checkout checkout;
//    ExtentTest test;
//    static ExtentReports report = new ExtentReports();
//    ExtentSparkReporter sparkReporter = new ExtentSparkReporter("report/Test_report.html");



 @Autowired
 ConfiguringProperties configuringProperties;

 @Before
 public void initializeObjects(){
     System.out.println("Initializing objects...");
     DriverSingleton.getInstance(configuringProperties.getBrowser());
     home = new Home();
     signInPage = new SignInPage();
     checkout = new Checkout();
 }

 @Given("^I visit the Website")
 public void i_visit_the_website(){
     driver = DriverSingleton.getDriver();
     System.out.println("iam here with driver"+ driver);
     driver.get(ConstantsKey.URL);
 }

 @When("^I click the SignIn button")
    public void i_click_the_signIn_button(){
    home.clickSignIn();
 }

 @When("^I add two products")
 public void i_add_two_products(){
     home.addFirstElement();
     home.addSecondElement();
 }

 @And("^I specify the credentials and click login")
    public void i_specify_the_credentials_and_click_login(){
    signInPage.loginAtInitial(configuringProperties.getEmail(), configuringProperties.getPasswd());

 }

 @And("^add i proceed to checkout")
 public void add_i_proceed_to_checkout(){
     checkout.checkOutProcess();
     checkout.clickPlaceOrder();
 }

 @And("^i confirm address,payment and final order")
    public void i_confirm_address_payment_and_final_order(){
     checkout.enterCardDetails(ConstantsKey.NAME, ConstantsKey.CARD_NUMBER, ConstantsKey.CVV_NUMBER,
             ConstantsKey.EXP_MONTH,ConstantsKey.EXP_YEAR);
 }

 @Then("^I can log into the website")
    public void i_can_log_into_the_website(){
     assertEquals(configuringProperties.getUsername(), home.getUserName());
 }

 @Then("^the products are bought")
    public void the_products_are_bought(){
     assertTrue(checkout.checkFinalConfirmation(ConstantsKey.ORDER_CONFIRMATION_MESSAGE));
//     assertEquals(true,checkout.checkFinalConfirmation(ConstantsKey.ORDER_CONFIRMATION_MESSAGE));
 }







}
