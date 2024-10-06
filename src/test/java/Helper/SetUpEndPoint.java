package Helper;

import LogicTest.ApiTest.Tools.EndPoint;

public class SetUpEndPoint {
    private static SetUpEndPoint helperClass;
    private static String URL;

    public static void setUpApi() {
        if (helperClass==null) {

            helperClass = new SetUpEndPoint();
        }
    }
    public static String getURL() {
        return URL;
    }

    public static void prepareURL(String url) {
        System.out.println("Prepare URL : " + url);
        URL = "";
        switch (url) {
            case "GET_LIST_USERS" :
                URL = EndPoint.GET_LIST_USERS;
                break;
            case "GET_PROFILE_USER" :
                URL = EndPoint.GET_PROFILE_USER;
                break;
            case "CREATE_NEW_USER" :
                URL = EndPoint.CREATE_NEW_USER;
                break;
            case "UPDATE_USER" :
                URL = EndPoint.UPDATE_USER;
                break;
            case "DELETE_USER" :
                URL = EndPoint.DELETE_USER;
                break;
            case "GET_LIST_TAGS" :
                URL = EndPoint.GET_LIST_TAGS;
                break;
            default:
                System.out.println("Please Input Right URL : ");
        }

        if(!url.isBlank()){
            System.out.println("URL is : " + URL);
        }
    }
}