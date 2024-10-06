package StepDef.ApiTest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import LogicTest.ApiTest.ApiList;
import LogicTest.ApiTest.ApiProcess;

public class ApiStep {
    ApiList ApiStep;

    public ApiStep(){
        ApiStep = new ApiList();
    }

    @When("hit api get list tags")
    public void hitApiGetListTags() {
        ApiStep.hitAPIGetLisTags();
    }

    @Then("validation response body get list tags")
    public void validationResponseBodyGetListTags() {
        System.out.println("validation response body list tag process");
        ApiStep.checkResponseBodyListTags();
    }

    @Then("validation status code api tag is equals {int}")
    public void validation_status_code_is_equals(Integer statusCode) {
        ApiProcess.validationStatusCode(ApiStep.getRes(), statusCode);
    }
}
