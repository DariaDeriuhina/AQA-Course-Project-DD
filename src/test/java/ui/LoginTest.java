package ui;

import base.BaseTest;
import com.codeborne.selenide.Selenide;
import data_provider.TestUIData;
import models.entity.User;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui_pages.kanboard.LoginPage;
import ui_pages.kanboard.MainPage;


public class LoginTest extends BaseTest{

    private LoginPage loginPage;
    private MainPage mainPage;

    @BeforeMethod
    public void setUpMethod() {
        Selenide.open("/login");
    }

    @Description("Positive: Check the login is successful with correct login and password")
    @Test(priority = 1, dataProvider = "correctUser", dataProviderClass = TestUIData.class)
    public void successfulAuthorizationTest(User user){
        loginPage = new LoginPage(driver);
        mainPage = loginPage.login(user);

        String expectedResult = mainPage.getPageTitle().getText();

        Assert.assertEquals(expectedResult, "KB Dashboard for admin");
    }

    @Description("Negative: Check the login is failed with incorrect login and password")
    @Test(priority = 2, dataProvider = "illegalLoginPassword", dataProviderClass = TestUIData.class)
    public void failedAuthorizationBothCredentialsTest(User user){
        loginPage = new LoginPage(driver);
        loginPage.login(user);

        String expectedMessage = loginPage.isLoginFailed().getErrorAlert().getText();

        Assert.assertEquals(expectedMessage, "Bad username or password");
    }

    @Description("Negative: Check the login is failed with incorrect password")
    @Test(priority = 3, dataProvider = "illegalPassword", dataProviderClass = TestUIData.class)
    public void failedAuthorizationPasswordTest(User user){
        loginPage = new LoginPage(driver);
        loginPage.login(user);

        String expectedMessage = loginPage.isLoginFailed().getErrorAlert().getText();

        Assert.assertEquals(expectedMessage, "Bad username or password");
    }

    @Description("Negative: Check the login is failed with incorrect login")
    @Test(priority = 4, dataProvider = "illegalLogin", dataProviderClass = TestUIData.class)
    public void failedAuthorizationLoginTest(User user){
        loginPage = new LoginPage(driver);
        loginPage.login(user);

        String expectedMessage = loginPage.isLoginFailed().getErrorAlert().getText();

        Assert.assertEquals(expectedMessage, "Bad username or password");
    }
}
