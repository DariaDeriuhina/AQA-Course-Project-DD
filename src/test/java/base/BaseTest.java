package base;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import static utils.EnvProperties.BASE_URL;
import static utils.EnvProperties.setUpInstance;

public class BaseTest {
    protected static WebDriver driver;

    @BeforeMethod
    @Parameters({"browserName", "headless"})
    public void setUp(String browserName, boolean headless) {
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
//        open("http://localhost/login");

        setUpInstance();
        Configuration.baseUrl = BASE_URL;
        Configuration.browser = browserName;
        Configuration.headless = headless;
    }

    @AfterMethod
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
