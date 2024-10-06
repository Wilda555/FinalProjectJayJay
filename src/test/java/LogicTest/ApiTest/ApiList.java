package LogicTest.ApiTest;

import Helper.SetUpEndPoint;
import io.restassured.response.Response;
import org.testng.Assert;
import LogicTest.ApiTest.Tools.ApiTag;

public class ApiList {
    private Response res;

    public Response getRes() {
        return res;
    }

    public void hitAPIGetLisTags() {
        res = ApiTag.getListTags(SetUpEndPoint.getURL());
        System.out.println(res.getBody().asString());
    }

    public void checkResponseBodyListTags() {
        System.out.println("test logic for check response body get List user");

        Assert.assertNotNull(res.getBody().jsonPath().get("data"));
    }
}
