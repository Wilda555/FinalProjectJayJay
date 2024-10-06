package LogicTest.WebTest;

import Helper.Utility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUp {

    @FindBy(id = "sign-username")
    private WebElement userName;

    @FindBy(id = "sign-password")
    private WebElement password;

    @FindBy(css = "button[onclick='register()']")
    private WebElement signUpButton;

    @FindBy(xpath = "//div[@id='signInModal']//button[@type='button'][normalize-space()='Close']")
    private WebElement cancelSignUpButton;

    public SignUp(){
        PageFactory.initElements(Utility.getDriver(), this);
    }

    public void fillUsername(String value) {
        userName.sendKeys(value);
    }

    public void fillPassword(String value) {
        password.sendKeys(value);
    }

    public void buttonSignUpClicked() {
        signUpButton.click();
    }

    public void buttonCancelSignUpClicked(){
        cancelSignUpButton.click();
    }
}
