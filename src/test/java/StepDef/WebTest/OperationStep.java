package StepDef.WebTest;

import Menu.WebTest.Item;
import Menu.WebTest.Order;
import Menu.WebTest.OrderPlace;
import Helper.Utility;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import LogicTest.WebTest.*;

import java.time.Duration;

public class OperationStep {
    OperationStep operationStep;
    Login loginFormPage;
    Contact formContact;
    VideoAboutUs aboutUsPopUp;
    CartPage cartPage;
    SignUp formSignUp;

    Order dataTestOrder;
    OrderPlace dataPlaceOrder;

    public OperationStep(){
        loginFormPage = new Login();
        OperationPage = new OperationPage();
        formContact = new Contact();
        aboutUsPopUp = new VideoAboutUs();
        dataTestOrder = new Order();
        cartPage = new CartPage();
        dataPlaceOrder = new OrderPlace();
        formSignUp = new SignUp();
    }

   @Given("user go to Product Store page {string}")
    public void navigateToWebApp(String url) throws InterruptedException {
        Utility.openPage(url);
        Thread.sleep(1000);
    }

    @And("user click menu Log in")
    public void navigateToLoginForm() throws InterruptedException {
        OperationPage.menuLoginClicked();
        Thread.sleep(1000);
    }

    @When("user input username {string}")
    public void entryUsernameField(String username) throws InterruptedException {
        loginFormPage.fillUsername(username);
        Thread.sleep(1000);
    }

    @And("user input password {string}")
    public void entryPasswordField(String password) throws InterruptedException {
        loginFormPage.fillPassword(password);
        Thread.sleep(1000);
    }

    @And("user click login button")
    public void clickButtonLogin() throws InterruptedException {
        loginFormPage.buttonLoginClicked();
        Thread.sleep(1000);
    }

    @Then("User login successfully with welcome message {string}")
    public void loginSuccessfully(String welcomeMessage) throws InterruptedException {
        OperationPage.verifyLoginSuccessfull(welcomeMessage);
        Thread.sleep(1000);
    }

    @And("User has been logged in")
    public void userHasBeenLoged() throws InterruptedException {
        navigateToLoginForm();
        entryUsernameField("test.wilda");
        entryPasswordField("Test123!");
        clickButtonLogin();
    }

    @When("User click menu Log out")
    public void userClickMenuLogOut() throws InterruptedException {
        OperationPage.menuLogoutClicked();

        WebDriverWait wait = new WebDriverWait(Utility.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@class='card-img-top img-fluid'])[1]")));
        Thread.sleep(1000);
    }

    @Then("User Log out Successfully and display menu {string}")
    public void userSuccessfullyLogOut(String menuSignUp) throws InterruptedException {
        OperationPage.verifyLogOutSuccessfully(menuSignUp);
        Thread.sleep(1000);
    }


    @And("user click menu Contact")
    public void userClickMenuContact() throws InterruptedException {
        OperationPage.menuContactClicked();
        Thread.sleep(1000);
    }

    @When("user input Contact Email {string}")
    public void userInputContactEmail(String contactEmail) throws InterruptedException {
        formContact.fillContactEmail(contactEmail);
        Thread.sleep(1000);
    }

    @And("user input Contact Name {string}")
    public void userInputContactName(String contactName) throws InterruptedException {
        formContact.fillContactName(contactName);
        Thread.sleep(1000);
    }

    @And("user input Message {string}")
    public void userInputMessage(String message) throws InterruptedException {
        formContact.fillMessage(message);
        Thread.sleep(1000);
    }

    @And("user click button Send Message")
    public void userClickButtonSendMessage() throws InterruptedException {
        formContact.buttonSendMessageClicked();
        Thread.sleep(3000);
    }

    @Then("Send Message successfully and display message {string}")
    public void sendMessageSuccessfully(String message) throws InterruptedException {
        OperationPage.verifyAllertMessage(message);
        Thread.sleep(2000);
        Utility.getDriver().switchTo().alert().accept();

        // wait for the new page to load
        WebDriverWait wait = new WebDriverWait(Utility.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@class='card-img-top img-fluid'])[1]")));
        Thread.sleep(1000);
    }


    @And("user click menu About Us")
    public void userClickMenuAboutUs() throws InterruptedException {
        OperationPage.menuAboutUsClicked();
        Thread.sleep(1000);
    }
    @When("user click button video")
    public void userClickButtonVideo() throws InterruptedException {
        aboutUsPopUp.videoClicked();
        Thread.sleep(1000);
    }
    @Then("Video played successfully")
    public void videoPlayedSuccessfully() throws InterruptedException {
        aboutUsPopUp.verifyVideoAlreadyPlaySuccessfully();
        Thread.sleep(1000);
    }

    @And("user already ordered Item")
    public void userAlreadyOrderedItem() throws InterruptedException {
        System.out.println("Step to ordered");

        userChoosedProductItem("Samsung galaxy s6");
        userClickAddToChart();
        Thread.sleep(2000);
        userShouldBeAbleSeeMessage("Product added");
        OperationPage.menuHomeClicked();
        Thread.sleep(1000);
        OperationPage.categoryMonitorsClicked();
        Thread.sleep(1000);
        userChoosedProductItem("ASUS Full HD");
        userClickAddToChart();
        Thread.sleep(2000);
        userShouldBeAbleSeeMessage("Product added");

        dataTestOrder.calculateTotalPrice();
        System.out.println("Total Price: " + dataTestOrder.getTotalPrice());
        Thread.sleep(1000);
    }

    @And("user click menu Cart")
    public void userClickMenuCart() throws InterruptedException {
        OperationPage.menuCartClicked();
        Thread.sleep(1000);
    }

    @When("user click button Place Order")
    public void userClickButtonPlaceOrder() throws InterruptedException {
        cartPage.buttonPlacerOrderClicked();
        Thread.sleep(1000);
    }

    @And("user input Name {string}")
    public void userInputName(String name) throws InterruptedException {
        cartPage.fillName(name);
        dataPlaceOrder.setName(name);
        Thread.sleep(1000);
    }

    @And("user input Country {string}")
    public void userInputCountry(String country) throws InterruptedException {
        cartPage.fillCountry(country);
        dataPlaceOrder.setCountry(country);
        Thread.sleep(1000);
    }

    @And("user input City {string}")
    public void userInputCity(String city) throws InterruptedException {
        cartPage.fillCity(city);
        dataPlaceOrder.setCity(city);
        Thread.sleep(1000);
    }

    @And("user input Credit Card {string}")
    public void userInputCreditCard(String creditCard) throws InterruptedException {
        cartPage.fillCreditCard(creditCard);
        dataPlaceOrder.setCreditCard(creditCard);
        Thread.sleep(1000);
    }

    @And("user input Moth {string}")
    public void userInputMoth(String month) throws InterruptedException {
        cartPage.fillMonth(month);
        dataPlaceOrder.setMonth(month);
        Thread.sleep(1000);
    }

    @And("user input Year {string}")
    public void userInputYear(String Year) throws InterruptedException {
        cartPage.fillYear(Year);
        dataPlaceOrder.setYear(Year);
        Thread.sleep(1000);
    }

    @And("user click button Purchase")
    public void userClickButtonPurchase() throws InterruptedException {
        cartPage.buttonPurchasedClicked();
        Thread.sleep(1000);
    }

    @Then("Order process successfully")
    public void orderProcessSuccessfully() throws InterruptedException {
        cartPage.verifyOrderSuccessfully(dataTestOrder.getTotalPrice(), dataPlaceOrder.getName(), dataPlaceOrder.getCreditCard());
        Thread.sleep(1000);
    }

    @When("user order product item {string}")
    public void userChoosedProductItem(String nameProduct) {
        OperationPage.ItemClicked(nameProduct);
        WebDriverWait wait = new WebDriverWait(Utility.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.btn-success.btn-lg")));
    }

    @And("User click Add to chart")
    public void userClickAddToChart() throws InterruptedException {
        OperationPage.buttonAddToCartClicked();
        Thread.sleep(2000);
    }

    @Then("user should be able see message {string}")
    public void userShouldBeAbleSeeMessage(String message) throws InterruptedException {
        OperationPage.verifyAllertMessage(message);
        Thread.sleep(2000);
        Utility.getDriver().switchTo().alert().accept();

        Item product = OperationPage.getInformationItemOrder();
        dataTestOrder.addItemOrder(product);
    }

    @And("user should be able see product ordered in the Cart Page")
    public void userShouldBeAbleSeeProductOrderedInTheCartPage() throws InterruptedException {

        OperationPage.menuCartClicked();

        String nameProduct = dataTestOrder.getDataOrdered().get(0).getName();
        WebDriverWait wait = new WebDriverWait(Utility.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.textToBePresentInElement(Utility.getDriver().findElement(By.xpath("//div[@id='page-wrapper']")), nameProduct));
        cartPage.verifyAddItemToCartPageSuccessfully(dataTestOrder);
        Thread.sleep(1000);

    }

    @And("user click menu Sign up")
    public void userClickMenuSignUp() throws InterruptedException{
        OperationPage.menuSignupClicked();
        Thread.sleep(1000);
    }

    @When("user input sign up username {string}")
    public void userInputSignUpUsername(String username) throws InterruptedException {
        formSignUp.fillUsername(username);
        Thread.sleep(1000);
    }

    @And("user input sign up password {string}")
    public void userInputSignUpPassword(String password) throws InterruptedException {
        formSignUp.fillPassword(password);
        Thread.sleep(1000);
    }

    @And("user click sign up button")
    public void userClickSignUpButton() throws InterruptedException {
        formSignUp.buttonSignUpClicked();
        Thread.sleep(3000);
    }

    @Then("Sign up successfully and display message {string}")
    public void signUpSuccessfullyWithMessage(String message) throws InterruptedException{
        OperationPage.verifyAllertMessage(message);
        Thread.sleep(2000);
        Utility.getDriver().switchTo().alert().accept();

        WebDriverWait wait = new WebDriverWait(Utility.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@class='card-img-top img-fluid'])[1]")));
        Thread.sleep(1000);
    }

    @When("user input sign up username and password with valid data")
    public void userInputSignUpUsernameWithValidData() throws InterruptedException {
        String username = Utility.generateRandomData();
        String password = Utility.generateRandomData();

        formSignUp.fillUsername(username);
        Thread.sleep(1000);
        formSignUp.fillPassword(password);
        Thread.sleep(1000);
    }

}
