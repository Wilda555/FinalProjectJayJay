package LogicTest.ApiTest.Tools;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiTag {
    private static RequestSpecification Tools;

    private static void setUpHeader(){
        Tools = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("app-id", "65b903ce6f8cd0533e947f68");
    }
    public static Response getListTags(String endpoint) {
        setUpHeader();
        return Tools.when().get(endpoint);
    }
}
