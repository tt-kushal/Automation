package automation.pages;

import automation.drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchFeature {

    private WebDriver driver;

    public SearchFeature(){
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "search")
    private WebElement searchBar;


    @FindBy(css = "#maincontent > div.columns > div.column.main > div.search.results > div.products.wrapper.grid.products-grid > ol > li:nth-child(1) > div")
    private WebElement searchResult;

    public Boolean searchElement(String searchStr){
        searchBar.sendKeys(searchStr);
        searchBar.submit();

        try {
            if (searchResult.isEnabled()){
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

}
