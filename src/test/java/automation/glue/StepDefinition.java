package automation.glue;

import automation.config.AutomationFrameworkConfiguration;
import automation.drivers.DriverSingleton;
import automation.pages.Checkout;
import automation.utility.TestCases;
import automation.utility.Utils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.java.Before;
import junit.framework.TestCase;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import automation.pages.Home;
import automation.pages.SignInPage;
import automation.utility.ConfiguringProperties;
import automation.utility.ConstantsKey;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//3 BDD method
@CucumberContextConfiguration
@ContextConfiguration(classes = AutomationFrameworkConfiguration.class )
public class StepDefinition {
    private WebDriver driver;
    private Home home;
    private SignInPage signInPage;
    private Checkout checkout;
    ExtentTest test;
    static ExtentReports report = new ExtentReports("report/testReport.html");



 @Autowired
 ConfiguringProperties configuringProperties;

 @Before
 public void initializeObjects(){
     System.out.println("Initializing objects...");
     DriverSingleton.getInstance(configuringProperties.getBrowser());
     home = new Home();
     signInPage = new SignInPage();
     checkout = new Checkout();
     TestCases[] testCases = TestCases.values();
     test = report.startTest(testCases[Utils.testCount].getTestName());
     Utils.testCount++;
 }

 @Given("^I visit the Website")
 public void i_visit_the_website(){
     driver = DriverSingleton.getDriver();
     System.out.println("iam here with driver"+ driver);
     driver.get(ConstantsKey.URL);
     test.log(LogStatus.PASS,"navigated to the website "+ConstantsKey.URL);// here we log our results
 }

 @When("^I click the SignIn button")
    public void i_click_the_signIn_button(){
    home.clickSignIn();
     test.log(LogStatus.PASS,"i have clicked the signIn button ");
 }


 @When("^I add two products")
 public void i_add_two_products(){
     home.addFirstElement();
     home.addSecondElement();
     test.log(LogStatus.PASS,"i have added two items into the cart ");
 }

 @And("^I specify the credentials and click login")
    public void i_specify_the_credentials_and_click_login(){
    signInPage.loginAtInitial(configuringProperties.getEmail(), configuringProperties.getPasswd());
     test.log(LogStatus.PASS,"i have added valid credentials and signed in ");
 }

 @And("^add i proceed to checkout")
 public void add_i_proceed_to_checkout(){
     checkout.checkOutProcess();
     checkout.clickPlaceOrder();
     test.log(LogStatus.PASS,"i proceed to check out");
 }

 @And("^i confirm address,payment and final order")
    public void i_confirm_address_payment_and_final_order(){
     checkout.enterCardDetails(ConstantsKey.NAME, ConstantsKey.CARD_NUMBER, ConstantsKey.CVV_NUMBER,
             ConstantsKey.EXP_MONTH,ConstantsKey.EXP_YEAR);
     test.log(LogStatus.PASS,"i confirm address,payment and final order ");
 }

 @Then("^I can log into the website")
    public void i_can_log_into_the_website(){
     if (configuringProperties.getUsername().equals(home.getUserName())){
         test.log(LogStatus.PASS, "Authentication is successful");
     }
     else {
         test.log(LogStatus.FAIL, "Authentication failed");
     }
     assertEquals(configuringProperties.getUsername(), home.getUserName());
 }

 @Then("^the products are bought")
    public void the_products_are_bought(){
     assertTrue(checkout.checkFinalConfirmation(ConstantsKey.ORDER_CONFIRMATION_MESSAGE));
//     assertEquals(true,checkout.checkFinalConfirmation(ConstantsKey.ORDER_CONFIRMATION_MESSAGE));
     test.log(LogStatus.PASS, "the products are bought");
 }

 @After
    public void closeObjects(){
     report.endTest(test);
     report.flush(); // it saves file to the disk
     DriverSingleton.closeMethod();
 }







}
