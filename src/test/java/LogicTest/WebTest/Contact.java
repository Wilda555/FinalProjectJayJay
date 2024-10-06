package LogicTest.WebTest;

import Helper.Utility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Contact {

    @FindBy(id = "recipient-email")
    private WebElement contactEmail;

    @FindBy(id = "recipient-name")
    private WebElement contactName;

    @FindBy(id = "message-text")
    private WebElement message;

    @FindBy(css = "button[onclick='send()']")
    private WebElement SendMessageButton;

    @FindBy(xpath = "//div[@id='exampleModal']//button[@type='button'][normalize-space()='Close']")
    private WebElement cancelSendMessageButton;

    public Contact(){
        PageFactory.initElements(Utility.getDriver(), this);
    }

    public void fillContactEmail(String value) {
        contactEmail.sendKeys(value);
    }

    public void fillContactName(String value) {
        contactName.sendKeys(value);
    }

    public void fillMessage(String value) {
        message.sendKeys(value);
    }

    public void buttonSendMessageClicked() {
        SendMessageButton.click();
    }

    public void buttonCancelSendMessageClicked(){
        cancelSendMessageButton.click();
    }
}
