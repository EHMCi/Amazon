import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class waitForElement {

    public static WebElement toBeVisible(WebDriver driver, String xPath, int seconds){

        WebDriverWait wait = new WebDriverWait(driver,seconds);

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));

        return element;
    }

    public static WebElement toBeClickable(WebDriver driver , WebElement webElement , int seconds){
        WebDriverWait wait = new WebDriverWait(driver,seconds);

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webElement));

        return webElement;

    }

}
