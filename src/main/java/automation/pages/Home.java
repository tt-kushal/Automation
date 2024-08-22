package automation.pages;

import automation.drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import automation.utility.ConstantsKey;
import automation.utility.Utils;

import java.time.Duration;

public class Home {
    private WebDriver driver;

    public Home(){
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "body > section:nth-child(3) > div > div > div.col-sm-9.padding-right > div.features_items > div:nth-child(3) > div > div.single-products > div.productinfo.text-center > a")
    private WebElement product1;

    @FindBy(css = "body > section:nth-child(3) > div > div > div.col-sm-9.padding-right > div.features_items > div:nth-child(4) > div > div.single-products > div.productinfo.text-center > a")
    private WebElement product2;

    @FindBy(css = "#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(3) > a")
    private WebElement cartButton;

    @FindBy(css = "#cartModal > div > div > div.modal-footer > button")
    private WebElement continueShopping;

    @FindBy(css = "#cartModal > div > div > div.modal-body > p:nth-child(2) > a")
    private WebElement viewCart;

    @FindBy(css = "#do_action > div.container > div > div > a")
    private WebElement proceedToCheckout;

    @FindBy(css = "body > section:nth-child(3) > div > div > div.col-sm-9.padding-right > div.features_items > div:nth-child(3) > div > div.single-products > div.productinfo.text-center")
    private WebElement hoverOnProduct1;

    @FindBy(css = "body > section:nth-child(3) > div > div > div.col-sm-9.padding-right > div.features_items > div:nth-child(7) > div > div.single-products > div.productinfo.text-center")
    private WebElement hoverOnProduct2;

    @FindBy(css = "#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(4) > a")
    private WebElement signInButton;

    @FindBy(css ="#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(10) > a")
    private WebElement userName;

    public void clickSignIn(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ConstantsKey.TIME_DURATION));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }

    public String getUserName(){
        return userName.getText();
    }

    public void addFirstElement(){
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverOnProduct1).build().perform();
        product1.click();
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(ConstantsKey.TIME_DURATION));
        wait.until(ExpectedConditions.elementToBeClickable(continueShopping));
        continueShopping.click();
        if (cartButton.getText().contains("Cart is empty! Click here to buy products.")){
            System.out.println("the cart is empty");

        }else{
            System.out.println("product added to cart successfully");
            Utils.takeScreenShots();
        }
    }

    public void addSecondElement(){
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverOnProduct2).build().perform();
        product2.click();
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(ConstantsKey.TIME_DURATION));
        wait.until(ExpectedConditions.elementToBeClickable(viewCart));
        viewCart.click();
    }

}
