package LogicTest.WebTest;

import Menu.WebTest.Item;
import Menu.WebTest.Order;
import Helper.Utility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CartPage {

    @FindBy(id = "totalp")
    private WebElement totalPrice;

    @FindBy(xpath = "//button[normalize-space()='Place Order']")
    private WebElement placeOrderButton;

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "country")
    private WebElement country;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "card")
    private WebElement creditCard;

    @FindBy(id = "month")
    private WebElement month;

    @FindBy(id = "year")
    private WebElement year;

    @FindBy(css = "button[onclick='purchaseOrder()']")
    private WebElement purchasedButton;

    @FindBy(xpath = "//div[@id='orderModal']//button[@type='button'][normalize-space()='Close']")
    private WebElement cancelOrderButton;

    @FindBy(css = "body > div:nth-child(19) > h2:nth-child(6)")
    private WebElement informationHeader;

    @FindBy(xpath = "//p[contains(@class,'lead text-muted')]")
    private WebElement InformationOrder;

    @FindBy(xpath = "//div[@id='orderModal']//button[@type='button'][normalize-space()='Close']")
    private WebElement okButton;

    @FindBy(id = "tbodyid")
    private WebElement tableOrder;

    public CartPage(){
        PageFactory.initElements(Utility.getDriver(), this);
    }

    public void buttonPlacerOrderClicked() {
        placeOrderButton.click();
    }

    public void fillName(String value) {
        name.sendKeys(value);
    }

    public void fillCountry(String value) {
        country.sendKeys(value);
    }

    public void fillCity(String value) {
        city.sendKeys(value);
    }

    public void fillCreditCard(String value) {
        creditCard.sendKeys(value);
    }

    public void fillMonth(String value) {
        month.sendKeys(value);
    }

    public void fillYear(String value) {
        year.sendKeys(value);
    }

    public void buttonPurchasedClicked() {
        purchasedButton.click();
    }

    public void verifyOrderSuccessfully(int exoectedTotalPrice, String nameCustomer, String crdCard){
        String expectedHeaderInformation = "Thank you for your purchase!";
        Assert.assertEquals(informationHeader.getText(), expectedHeaderInformation);

        DateFormat dform = new SimpleDateFormat("dd/MM/yyyy");
        Date obj = new Date();
        String currentDate = dform.format(obj);

        String expectedAmount = "Amount: " + exoectedTotalPrice + " USD";
        String expectedCardNumber = "Card Number: " + crdCard;
        String expectedName = "Name: " + nameCustomer;
        String expectedDate = "Date: " + currentDate;

        String[] dataOrder = InformationOrder.getText().split("\\r?\\n|\\r");

        String[] actualID = dataOrder[0].split(":");
        String actualAmount = dataOrder[1];
        String actualNumber = dataOrder[2];
        String actualName = dataOrder[3];
        String actualDate = dataOrder[4];

        Assert.assertEquals(actualID[0].trim(),"Id");
        Assert.assertTrue(actualID[1].trim().matches("[0-9]+"));
        Assert.assertEquals(actualAmount, expectedAmount);
        Assert.assertEquals(actualNumber, expectedCardNumber);
        Assert.assertEquals(actualName, expectedName);
        Assert.assertEquals(actualDate, expectedDate);
    }

    public void verifyAddItemToCartPageSuccessfully(Order dataTestOrder) {
        dataTestOrder.calculateTotalPrice();
        System.out.println("Total Harga " + dataTestOrder.getTotalPrice());


        System.out.println(tableOrder.getText());
        String[] actualProduct = tableOrder.getText().split("\\r?\\n|\\r");


        int actualTotalPrice = 0;
        if (!totalPrice.getText().isBlank()){
            actualTotalPrice = Integer.parseInt(totalPrice.getText());
        }
        Assert.assertEquals(actualTotalPrice, dataTestOrder.getTotalPrice());
        Assert.assertEquals(actualProduct.length,dataTestOrder.getDataOrdered().size());
        for(int i =0; i< dataTestOrder.getDataOrdered().size(); i++){
            Item itemProduct = dataTestOrder.getDataOrdered().get(i);
            String actualDataProduct = actualProduct[i].replace("Delete","");
            String expectedDataProduct = itemProduct.getName()+" "+itemProduct.getPrice();
            Assert.assertEquals(actualDataProduct.trim(), expectedDataProduct.trim());
        }
    }
}
