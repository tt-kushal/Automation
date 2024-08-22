package automation.pages;

import automation.drivers.DriverSingleton;
import automation.utility.ConstantsKey;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage {
    private WebDriver driver;

    public SignInPage(){
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#form > div > div > div.col-sm-4.col-sm-offset-1 > div > form > input[type=email]:nth-child(2)")
    private WebElement emailField;

    @FindBy(css = "#form > div > div > div.col-sm-4.col-sm-offset-1 > div > form > input[type=password]:nth-child(3)")
    private WebElement passwordField;

    @FindBy(css = "#form > div > div > div.col-sm-4.col-sm-offset-1 > div > form > button")
    private WebElement loginButton;

    @FindBy(css = "#form > div > div > div:nth-child(3) > div > form > input[type=text]:nth-child(2)")
    private WebElement nameField;

    @FindBy(css = "#form > div > div > div:nth-child(3) > div > form > input[type=email]:nth-child(3)")
    private WebElement emailAddress;

    @FindBy(css = "#form > div > div > div:nth-child(3) > div > form > button")
    private WebElement signupButton;

    @FindBy(css = "#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(3) > a")
    private WebElement cartButton;

    public void loginFeature(String email, String passwd){
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(ConstantsKey.TIME_DURATION));
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        emailField.sendKeys(email);
        passwordField.sendKeys(passwd);
        loginButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        cartButton.click();

    }

    public void loginAtInitial(String email, String passwd){
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(ConstantsKey.TIME_DURATION));
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        emailField.sendKeys(email);
        passwordField.sendKeys(passwd);
        loginButton.click();
    }

    public void signupFeature(String name, String emailAdrs){
        nameField.sendKeys(name);
        emailAddress.sendKeys(emailAdrs);
        signupButton.click();
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(ConstantsKey.TIME_DURATION));
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        cartButton.click();
    }
}
