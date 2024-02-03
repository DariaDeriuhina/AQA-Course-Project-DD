package api.groped_steps;

import api.steps.SessionApiSteps;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;


public class GroupedLoginSteps {
    SessionApiSteps sessionApiSteps = new SessionApiSteps();

    public void loginViaApi(String username, String password) {
        open("/favicon.ico");
        Response responseAuth = sessionApiSteps.sendAuthRequest(baseUrl);
        String cookieSID = sessionApiSteps.getCookieSID(responseAuth);
        String cookieRM = sessionApiSteps.getRMCookie(
                sessionApiSteps.getCSRFToken(responseAuth), cookieSID, username, password, baseUrl);
        setSessionCookies(cookieSID, cookieRM);
        open("");
    }

    private void setSessionCookies(String cookieSID, String cookieRM) {
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie(SessionApiSteps.KB_SID, cookieSID));
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie(SessionApiSteps.KB_RM, cookieRM));
    }
}
