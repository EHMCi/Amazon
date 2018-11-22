import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class page extends Abstract{

    public static WebElement findXpath(String value){
        WebElement elem = driver.findElement(By.xpath(value));
        return elem;
    }
    public static WebElement findId(String value){
        WebElement element =  driver.findElement(By.id(value));
    return element;
    }
    public static WebElement findClass(String value){
        WebElement element = driver.findElement(By.className(value));
    return element;
    }

}
