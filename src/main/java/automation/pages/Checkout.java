package automation.pages;

import automation.drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import automation.utility.ConstantsKey;

import java.time.Duration;

public class Checkout {
    private WebDriver driver;

    public Checkout(){
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#do_action > div.container > div > div > a")
    private WebElement checkoutButton ;

    @FindBy(css = "#cart_items > div > div:nth-child(2) > h2")
    private WebElement addressDetailsTitle;

    @FindBy(css = "#checkoutModal > div > div > div.modal-body > p:nth-child(2) > a")
    private WebElement loginPage;

    @FindBy(css = "#cart_items > div > div:nth-child(7) > a")
    private WebElement placeOrderButton;

    @FindBy(css = "#payment-form > div:nth-child(2) > div > input")
    private WebElement cardName;

    @FindBy(css = "#payment-form > div:nth-child(3) > div > input")
    private WebElement cardNumber;

    @FindBy(css = "#payment-form > div:nth-child(4) > div.col-sm-4.form-group.cvc > input")
    private WebElement cvvNumber;

    @FindBy(css = "#payment-form > div:nth-child(2) > div > input")
    private WebElement expirationMonth;

    @FindBy(css = "#payment-form > div:nth-child(4) > div:nth-child(3) > input")
    private WebElement expirationYear;

    @FindBy(id = "submit")
    private WebElement payAndConfirmButton;

    @FindBy(css = "#form > div > div > div > h2 > b")
    private WebElement orderConfirmationMessage;

    @FindBy(css = "#cart_items > div > div.breadcrumbs > ol > li.active")
    private WebElement cartPageConfirmation;

    public void checkOutProcess(){
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(ConstantsKey.EXTRA_TIME_DURATION));
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutButton.click();
    }

    public String addressTitle(){
        return addressDetailsTitle.getText();
    }

    public void clickPlaceOrder(){
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(ConstantsKey.TIME_DURATION));
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
        placeOrderButton.click();
        System.out.println("order placed successfully");
    }

    public void enterCardDetails(String name, String cardNum, String cvvNum, String expMonth, String expYear){
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(ConstantsKey.TIME_DURATION));
        wait.until(ExpectedConditions.visibilityOf(cardName));
        cardName.clear();
        cardName.sendKeys(name);

        cardNumber.clear();
        cardNumber.sendKeys(cardNum);

        cvvNumber.clear();
        cvvNumber.sendKeys(cvvNum);

        expirationMonth.clear();
        expirationMonth.sendKeys(expMonth);

        expirationYear.clear();
        expirationYear.sendKeys(expYear);

        payAndConfirmButton.submit();
        System.out.println("oder placed");
    }

    public Boolean checkFinalConfirmation (String title){
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(ConstantsKey.TIME_DURATION));
        wait.until(ExpectedConditions.visibilityOf(orderConfirmationMessage));
        return orderConfirmationMessage.getText().contains(ConstantsKey.ORDER_CONFIRMATION_MESSAGE);
    }

    public String cartPageConfirmation(){
        return cartPageConfirmation.getText();

    }

    public boolean cartPageConfirmation2(){
        return cartPageConfirmation.getText().contains(ConstantsKey.CART_CONFIRMATION);

    }

}
