import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Abstract {

    public static WebDriver driver;
    public static String baseUrl = "https://www.amazon.com/";

    @BeforeClass
    public static void setUp(){

        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }
/*
    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
*/
}
