package LogicTest.ApiTest.Tools;

import Menu.ApiTest.UserProfile;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiUserTools {
    private static RequestSpecification Tools;

    private static void setUpHeader(){
        Tools = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("app-id", "65b903ce6f8cd0533e947f68");
    }

    public static Response getListUser(String endpoint){
        setUpHeader();
        return Tools.when().get(endpoint);
    }

    public static Response getProfileUser(String url, String idUser) {
        setUpHeader();
        String endpoint = url + idUser;
        System.out.println("Final Endpoint : " + endpoint);
        return Tools.when().get(endpoint);
    }

    public static Response postCreateUser(String endpoint, UserProfile dataUser) {
        Gson payload = new Gson();
        System.out.println("Gson :" + payload.toJson(dataUser));
        setUpHeader();
        return  Tools.body(payload.toJson(dataUser)).when().post(endpoint);
    }

    public static Response putUser(String url, UserProfile dataUserUpdate, String idUser) {
        String endpoint = url + idUser;
        Gson payload = new Gson();
        System.out.println("Gson :" + payload.toJson(dataUserUpdate));
        System.out.println(endpoint);
        setUpHeader();
        return  Tools.body(payload.toJson(dataUserUpdate)).when().put(endpoint);
    }

    public static Response deleteUserById(String url, String idUser) {
        String endpoint = url + idUser;
        setUpHeader();
        return Tools.when().delete(endpoint);
    }
}
