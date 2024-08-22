import automation.drivers.DriverSingleton;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import automation.pages.SearchFeature;
import automation.utility.ConstantsKey;
import automation.utility.FrameworkProperties;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SearchTest {

    static DriverSingleton driverSingleton;
    static WebDriver driver;
    static FrameworkProperties frameworkProperties;
    static SearchFeature searchFeature;
    static String inputString;
    static Boolean expectedResults;

    @BeforeClass
    public static void initializeObject(){
        frameworkProperties = new FrameworkProperties();
        DriverSingleton.getInstance(frameworkProperties.getProperty(ConstantsKey.BROWSER));
        driver = DriverSingleton.getDriver();
        searchFeature = new SearchFeature();
    }

   // call constructor
    public SearchTest(String inputString, Boolean expectedResults){
        this.inputString = inputString;
        this.expectedResults = expectedResults;
    }

   @Parameterized.Parameters
    public static Collection searchOptions(){
        return Arrays.asList(new Object[][]{
                {"shirt", true},
                {"pant", true},
                {"", false},
                {"test", false},

        });
    }

    @Test
    public void testingSearch(){
        driver.get(ConstantsKey.URL1);
        assertEquals(expectedResults, searchFeature.searchElement(inputString));

    }
    
    @AfterClass
    public static void closeObjects(){
        driver.quit();
    }

}
