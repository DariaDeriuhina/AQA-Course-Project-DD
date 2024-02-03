package api;

import api.groped_steps.GroupedLoginSteps;
import api.steps.UserApiSteps;
import base.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class LoginTest extends BaseTest {
    private static final String USERNAME = "user"+ randomAlphanumeric(10);
    private static final String PASSWORD = "myTestPassword";
    private String userId;
    UserApiSteps userApiSteps = new UserApiSteps();
    GroupedLoginSteps groupedLoginSteps = new GroupedLoginSteps();


    @BeforeMethod
    public void prepareDataForTest() {
        userId = userApiSteps.createUser(USERNAME, PASSWORD);
    }

    @Test
    public void loginApi() {
        groupedLoginSteps.loginViaApi(USERNAME, PASSWORD);
    }

    @AfterMethod
    public void removeUserAfterTest(){
        userApiSteps.deleteUser(userId);
    }

}
