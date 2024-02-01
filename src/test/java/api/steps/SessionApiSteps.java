package api.steps;

import io.restassured.response.Response;
import org.jsoup.Jsoup;

import static io.restassured.RestAssured.given;

public class SessionApiSteps extends BaseApiSteps {
    public static final String KB_SID = "KB_SID";
    public static final String KB_RM = "KB_RM";
    private static final String AUTH_CONTROLLER = "AuthController";
    private static final String CONTROLLER = "controller";
    private static final String CSRF_TOKEN = "csrf_token";
    private static final String ACTION = "action";

    public Response sendAuthRequest(String url) {
        return given().queryParam(CONTROLLER, AUTH_CONTROLLER)
                .queryParam(ACTION, "login")
                .when()
                .get(url);
    }

    public String getCookieSID(Response responseAuth) {
        return responseAuth.getCookie(KB_SID);
    }

    public String getCSRFToken(Response responseAuth) {
        return Jsoup.parseBodyFragment(responseAuth.body().asString())
                .getElementsByAttributeValue("name", CSRF_TOKEN)
                .attr("value");
    }

    public String getRMCookie(String cSRFToken, String cookieSID, String userName, String password, String uiUrl) {
        Response response02 = given().queryParam(CONTROLLER, AUTH_CONTROLLER)
                .queryParam(ACTION, "check")
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("remember_me", "1")
                .formParam("username", userName)
                .formParam("password", password)
                .formParam(CSRF_TOKEN, cSRFToken)
                .cookie(KB_SID, cookieSID).when().post(uiUrl);
        return response02.getCookie(KB_RM);
    }
}
