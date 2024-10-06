package LogicTest.ApiTest;

import Menu.ApiTest.UserProfile;
import com.google.gson.Gson;
import Helper.SetUpEndPoint;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import LogicTest.ApiTest.Tools.ApiUserTools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiUserLogic {

    private Response res;

    public Response getRes() {
        return res;
    }

    public void setRes(Response res) {
        this.res = res;
    }

    //hit api get list users
    public void hitApiGetListUser(){
        res = ApiUserLogic.getListUser(SetUpEndPoint.getURL());
        System.out.println(res.getBody().asString());
    }

        public void checkResponseBodyListUsers(){
        System.out.println("validation response body list user process");
        JSONArray data = new JSONArray(res.getBody().jsonPath().getList("data"));
        for(int i=0; i<data.length(); i++){
            JSONObject user = (JSONObject) data.get(i);

            Assert.assertNotNull(user.get("id"));
            assertThat(user.get("title")).isIn("mr", "ms", "mrs", "miss", "dr", "");
            Assert.assertNotNull(user.get("firstName"));
            Assert.assertNotNull(user.get("lastName"));
        }
    }

    public void hitApiGetProfileUser(String idUser) {
        res = ApiUserTools.getProfileUser(SetUpEndPoint.getURL(), idUser);
        System.out.println(res.getBody().asString());
    }

    public void checkResponseBodyProfileUser() {
        System.out.println("validation response body profile user process normal");
        JSONObject userProfile = new JSONObject(res.getBody().asString());

        Assert.assertNotNull(userProfile.get("id"));
        assertThat(userProfile.get("title")).isIn("mr", "ms", "mrs", "miss", "dr", "");
        Assert.assertNotNull(userProfile.get("firstName"));
        Assert.assertNotNull(userProfile.get("lastName"));
        assertThat(userProfile.get("gender")).isIn("male", "female", "");
    }

    public void checkResponseBodyGetProfileUserFailed(String expectedMessage) {
        System.out.println("validation response body profile user process failed");
        JSONObject notification = new JSONObject(res.getBody().asString());


        String actualMessage = notification.get("error").toString();
        System.out.println("actual message: " + actualMessage);
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    public String hitApiPostNewUser(UserProfile dataUser) {
        res = ApiUserTools.postCreateUser(SetUpEndPoint.getURL(), dataUser);
        System.out.println(res.getBody().asString());

        return res.getBody().jsonPath().get("id");
    }

    public String hitApiPostNewUser(String endPoint, UserProfile dataUser) {
        res = ApiUserTools.postCreateUser(endPoint, dataUser);
        System.out.println(res.getBody().asString());

        return res.getBody().jsonPath().get("id");
    }

    public void hitApiUpdateProfileUser(UserProfile dataUser, String idUser) {
        res = ApiUserTools.putUser(SetUpEndPoint.getURL(), dataUser, idUser);
        System.out.println(res.getBody().asString());
    }

    public void checkResponseBodyCreateUser(UserProfile dataTestUser) throws ParseException {
        System.out.println("test logic for check response body create user");

        Gson json = new Gson();
        UserProfile actualData = json.fromJson(res.getBody().asString(), UserProfile.class);
        System.out.print("Actual Data : ");
        System.out.println(json.toJson(actualData));
        System.out.print("Test Data : ");
        System.out.println(json.toJson(dataTestUser));
        Assert.assertEquals(actualData.getTitle(), dataTestUser.getTitle());
        Assert.assertEquals(actualData.getFirstName(), dataTestUser.getFirstName());
        Assert.assertEquals(actualData.getLastName(), dataTestUser.getLastName());
        Assert.assertEquals(actualData.getPicture(), dataTestUser.getPicture());
        Assert.assertEquals(actualData.getGender(), dataTestUser.getGender());
        Assert.assertEquals(actualData.getEmail(), dataTestUser.getEmail());
        Assert.assertEquals(actualData.getDateOfBirth(), dataTestUser.getDateOfBirth());
        Assert.assertEquals(actualData.getPhone(), dataTestUser.getPhone());
        Assert.assertEquals(actualData.getLocation().getStreet(), dataTestUser.getLocation().getStreet());
        Assert.assertEquals(actualData.getLocation().getCity(), dataTestUser.getLocation().getCity());
        Assert.assertEquals(actualData.getLocation().getState(), dataTestUser.getLocation().getState());
        Assert.assertEquals(actualData.getLocation().getCountry(), dataTestUser.getLocation().getCountry());
        Assert.assertEquals(actualData.getLocation().getTimezone(), dataTestUser.getLocation().getTimezone());
        SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Assert.assertEquals(sdfDate.format(sdfTime.parse(actualData.getRegisterDate())), sdfDate.format(new Date()));
        Assert.assertEquals(sdfDate.format(sdfTime.parse(actualData.getUpdatedDate())), sdfDate.format(new Date()));

    }

    public void checkResponseBodyUpdateProfileUser(UserProfile dataTestUser, String idUserUpdate) throws ParseException {
        System.out.println("test logic for check response body update user");

        Gson json = new Gson();
        UserProfile actualData = json.fromJson(res.getBody().asString(), UserProfile.class);
        System.out.print("Actual Data : ");
        System.out.println(json.toJson(actualData));
        System.out.print("Test Data : ");
        System.out.println(json.toJson(dataTestUser));
        Assert.assertEquals(actualData.getId(), idUserUpdate);
        Assert.assertEquals(actualData.getTitle(), dataTestUser.getTitle());
        Assert.assertEquals(actualData.getFirstName(), dataTestUser.getFirstName());
        Assert.assertEquals(actualData.getLastName(), dataTestUser.getLastName());
        Assert.assertEquals(actualData.getPicture(), dataTestUser.getPicture());
        Assert.assertEquals(actualData.getGender(), dataTestUser.getGender());
        Assert.assertEquals(actualData.getDateOfBirth(), dataTestUser.getDateOfBirth());
        Assert.assertEquals(actualData.getPhone(), dataTestUser.getPhone());
        Assert.assertEquals(actualData.getLocation().getStreet(), dataTestUser.getLocation().getStreet());
        Assert.assertEquals(actualData.getLocation().getCity(), dataTestUser.getLocation().getCity());
        Assert.assertEquals(actualData.getLocation().getState(), dataTestUser.getLocation().getState());
        Assert.assertEquals(actualData.getLocation().getCountry(), dataTestUser.getLocation().getCountry());
        Assert.assertEquals(actualData.getLocation().getTimezone(), dataTestUser.getLocation().getTimezone());
        SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Assert.assertEquals(sdfDate.format(sdfTime.parse(actualData.getRegisterDate())), sdfDate.format(new Date ()));
        Assert.assertEquals(sdfDate.format(sdfTime.parse(actualData.getUpdatedDate())), sdfDate.format(new Date()));

    }

    public void hitAPIDeleteUserById(String idUser) {
        res = ApiUserTools.deleteUserById(SetUpEndPoint.getURL(), idUser);
        System.out.println("response after hit API delete");
        System.out.println(res.getBody().asString());
    }

    public void checkResponseBodyDeleteUser(String idUserDelete) {
        System.out.println("test logic for check response body delete user");

        System.out.println("id Delete:" + res.getBody());
        Assert.assertNotNull(res.getBody().jsonPath().get("id"));

    }
}
