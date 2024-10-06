package LogicTest.WebTest;

import Helper.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class OperationPage {

    @FindBy(xpath = "//li[@class='nav-item active']//a[@class='nav-link']")
    private WebElement menuHome;

    @FindBy(xpath = "//a[normalize-space()='Contact']")
    private WebElement menuContact;

    @FindBy(xpath = "//a[normalize-space()='About us']")
    private WebElement menuAboutUs;

    @FindBy(id = "cartur")
    private WebElement menuCart;

    @FindBy(id = "login2")
    private WebElement menuLogin;

    @FindBy(id = "signin2")
    private WebElement menuSignup;

    @FindBy(id = "nameofuser")
    private WebElement welcomeUser;

    @FindBy(css = ".btn.btn-success.btn-lg")
    private WebElement buttonAddToCart;

    @FindBy(xpath = "/html/body/div[5]/div/div[1]/div/a[4]")
    private WebElement categoryMonitors;

    @FindBy(id = "logout2")
    private WebElement logOut;


    public OperationPage(){
        PageFactory.initElements(Utility.getDriver(), this);
    }

    public void menuHomeClicked(){
        menuHome.click();
    }

    public void menuContactClicked(){
        menuContact.click();
    }

    public void menuAboutUsClicked(){
        menuAboutUs.click();
    }

    public void menuCartClicked(){
        menuCart.click();
    }

    public void menuLoginClicked(){
        menuLogin.click();
    }

    public void menuSignupClicked(){
        menuSignup.click();
    }

    public void verifyLoginSuccessfull(String expectedWelcomingMessage){
        WebDriverWait wait = new WebDriverWait(Utility.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));

        System.out.println("Actual welcoming message : " + welcomeUser.getText());

        Assert.assertEquals(welcomeUser.getText(), expectedWelcomingMessage); // welcoming message is match
    }

    public void ItemClicked(String nameProduct){
        WebElement link = Utility.getDriver().findElement(By.linkText(nameProduct));
        link.click();
    }

    public void buttonAddToCartClicked(){
        buttonAddToCart.click();
    }

    public void categoryMonitorsClicked(){
        categoryMonitors.click();
    }

    public void menuLogoutClicked(){
        logOut.click();
    }


    public void verifyAllertMessage(String expectedMessage) {
        String getAllertMessage = Utility.getDriver().switchTo().alert().getText();
        System.out.println(getAllertMessage);
        Assert.assertEquals(getAllertMessage, expectedMessage);
    }

    public void verifyLogOutSuccessfully(String expectedMessage) {
        Assert.assertEquals(menuSignup.getText(), expectedMessage);
    }

    public Item getInformationItemOrder(){
        System.out.println("Get data information order");
        String nameProduct = Utility.getDriver().findElement(By.cssSelector(".name")).getText();
        String priceProduct = Utility.getDriver().findElement(By.cssSelector(".price-container")).getText();

        priceProduct = priceProduct.replaceAll("[^0-9]", "");
        System.out.println("get price : " + priceProduct);

        Item product = new Item();
        product.setName(nameProduct);
        product.setPrice(Integer.parseInt(priceProduct));

        return product;

    }
}
