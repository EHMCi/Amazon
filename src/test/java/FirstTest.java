import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.TestInstance;

public class FirstTest extends page{

    private String product = null;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }


    //1. http://www.amazon.com sitesine gelecek ve anasayfanin acildigini onaylayacak,
    @Test(priority=1)
    static void MainPageControl(){
        Assert.assertEquals(driver.getTitle(),"Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.amazon.com/");
    }

    //2. Login ekranini acip, bir kullanici ile login olacak ( daha once siteye
    //uyeligi varsa o olabilir )
    @Test(priority=2)
    public static void loginControl(){
        String signButton = findId("nav-link-accountList").getText();
        Assert.assertEquals(signButton,"Hello. Sign in\n" +
                "Account & Lists");
        findId("nav-link-accountList").click();
        findId("ap_email").sendKeys("onurhosgor@yandex.com");
        findId("ap_password").sendKeys("testtesttest");
        findId("signInSubmit").click();
    }


    //3. Ekranin ustundeki Search alanina &#39;samsung&#39; yazip Ara butonuna
    //tiklayacak,
    @Test(priority=3)
    public void searchControl() {
        findId("twotabsearchtextbox").sendKeys("samsung");
        findXpath("//div[@class='nav-search-submit nav-sprite']/input[@value='Go']").click();

    }
    //4. Gelen sayfada samsung icin sonuc bulundugunu onaylayacak,
    @Test(priority=4)
    public void responseSearchControl(){
        String text = waitForElement.toBeVisible(driver,"//span[@id='s-result-count']/span/span",10).getText().toLowerCase();
        Assert.assertEquals(text,"\"samsung\"");
            }
    //5. Arama sonuclarindan 2. sayfaya tiklayacak ve acilan sayfada 2.
    //sayfanin su an gosterimde oldugunu onaylayacak,
    @Test(priority=5)
    public void openPage2Control(){

        WebElement page2Button = findXpath("//span[@class='pagnLink']/a[text()='2']");

        waitForElement.toBeClickable(driver, page2Button , 10).click();

        String pageText = waitForElement.toBeVisible(driver, "//span[@class='pagnCur']" , 10).getText();

        Assert.assertEquals(pageText,"2");
    }

    //6. Ustten 3. urunun icindeki &#39;Add to List&#39; butonuna tiklayacak,
    @Test(priority=6)
    public void addListControl() {

        WebElement product3 = driver.findElement(By.xpath("//*[@id='s-results-list-atf']/li[3]//a/h2"));
        waitForElement.toBeClickable(driver, product3, 10).click();
        String product = waitForElement.toBeVisible(driver, "//*[@id='productTitle']", 10).getText();
        setProduct(product);
        WebElement element = findId("add-to-wishlist-button-submit");//add-to-wishlist-button-submit  //masrw-mas-add-to-wish-list-announce
        waitForElement.toBeClickable(driver, element, 10).click();
        }
    //7. Ekranin en ustundeki &#39;List&#39; linkine tiklayacak içerisinden Wish listi
    //seçecek,
    //8. Acilan sayfada bir onceki sayfada izlemeye alinmis urunun bulundugunu
    //onaylayacak,
    @Test(priority=7)
    public void openWishListControl() throws InterruptedException {
        Thread.sleep(7000);
        WebElement element = findXpath("//span[@id='WLHUC_result_listName']/a");
        waitForElement.toBeClickable(driver, element, 10).click();
        Thread.sleep(7000);
        String productName = findXpath("//*[@id=\"g-items\"]//a[@title='"+getProduct()+"']").getAttribute("title");
        //String productName = findXpath("//*[@id='g-items']/li[1]//a[@title='"+getProduct()+"']").getAttribute("title");
        Assert.assertEquals(productName,getProduct());

    }

    //9. Favorilere alinan bu urunun yanindaki &#39;Delete&#39; butonuna basarak,
    //favorilerimden cikaracak,
    //10. Sayfada bu urunun artik favorilere alinmadigini onaylayacak.

    @Test(dependsOnMethods = "openWishListControl")
    public void productControl() throws InterruptedException {

        WebElement element = findXpath("//*[@id=\"a-autoid-7\"]");
        waitForElement.toBeClickable(driver,element,10).click();
        driver.navigate().refresh();
        Thread.sleep(7000);
        if(driver.findElements(By.xpath("//*[@id=\"g-items\"]//a[@title='"+product+"']")).size()==0){
            Assert.assertEquals(1,1);
            System.out.println("Bu ürün listede yoktur.");
        }else{
            Assert.assertEquals(1,0);
            System.out.println("Bu ürün var.");
        }
    }




}

