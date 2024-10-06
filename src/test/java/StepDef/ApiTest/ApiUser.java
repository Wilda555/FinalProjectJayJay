package StepDef.ApiTest;

import LogicTest.ApiTest.ApiUserLogic;
import Menu.ApiTest.UserProfile;
import Helper.SetUpEndPoint;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import LogicTest.ApiTest.ApiProcess;
import LogicTest.ApiTest.Tools.EndPoint;

import java.text.ParseException;

public class ApiUserStep {
    ApiUserLogic apiUser;
    UserProfile dataTestCreateUser, dataTestUpdateUser;
    String currentUserID;

    public ApiUserStep(){
        apiUser = new ApiUserLogic();
    }

    @Given("prepare url for {string}")
    public void prepare_url_for(String endpoint) {
        SetUpEndPoint.prepareURL(endpoint);
    }
    @Then("validation response json with JSONSchema {string}")
    public void validation_response_json_with_json_schema(String dataType) {
        ApiProcess.validationResponseData(apiUser.getRes(), dataType);
    }
    @Then("validation status code api user is equals {int}")
    public void validation_status_code_is_equals(Integer statusCode) {
        ApiProcess.validationStatusCode(apiUser.getRes(), statusCode);
    }

     @When("hit api get list users")
    public void hit_api_get_list_users() {
        apiUser.hitApiGetListUser();
    }
    @Then("validation response body get list users")
    public void validation_response_body_get_list_users() {

        apiUser.checkResponseBodyListUsers();
    }

    @When("hit api get profile user by id {string}")
    public void hitApiGetProfileUserById(String idUser) {

        apiUser.hitApiGetProfileUser(idUser);
    }
    @Then("validation response body get profile user")
    public void validationResponseBodyGetProfileUser() {
        apiUser.checkResponseBodyProfileUser();
    }
    @Then("validation response body with message {string}")
    public void validationResponseBodyFailedWithMessage(String message) {
        apiUser.checkResponseBodyGetProfileUserFailed(message);

    @When("hit api post create new user")
    public void hitApiPostCreateNewUser() {
        dataTestCreateUser = ApiProcess.prepareDataUserTestPost();
        apiUser.hitApiPostNewUser(dataTestCreateUser);
    }
    @Then("validation response body post create new user")
    public void validationResponseBodyPostCreateNewUser() throws ParseException {
        apiUser.checkResponseBodyCreateUser(dataTestCreateUser);
    }

    @And("hit api post create new user for manipulation data")
    public void hitApiPostCreateNewUserForManipulationData() {
        dataTestCreateUser = ApiProcess.prepareDataUserTestPost();
        currentUserID = apiUser.hitApiPostNewUser(EndPoint.CREATE_NEW_USER, dataTestCreateUser);
        System.out.println("current id after created : " + currentUserID);
    }

    @When("hit api update profile user")
    public void hitApiUpdateProfileUserById() {
        dataTestUpdateUser = ApiProcess.prepareDataUserTestUpdate();
        apiUser.hitApiUpdateProfileUser(dataTestUpdateUser, currentUserID);
    }
    @Then("validation response body update user")
    public void validationResponseBodyUpdateUser() throws ParseException {
        apiUser.checkResponseBodyUpdateProfileUser(dataTestUpdateUser, currentUserID);
    }

    @When("hit api delete user")
    public void hitApiDeleteUserForId() {
        apiUser.hitAPIDeleteUserById(currentUserID);
    }
    @Then("validation response body delete user")
    public void validationResponseBodyDeleteUser() {
        apiUser.checkResponseBodyDeleteUser(currentUserID);
    }
    @When("hit api get profile user after deleted")
    public void hitApiGetProfileUserAfterDeleted() {
        apiUser.hitApiGetProfileUser(currentUserID);
    }

    @When("hit api delete user for id {string}")
        public void hitApiDeleteUserForId(String idUser) {
            apiUser.hitAPIDeleteUserById(idUser);
        }
    }
