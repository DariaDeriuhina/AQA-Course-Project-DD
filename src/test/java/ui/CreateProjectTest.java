package ui;

import api.groped_steps.GroupedLoginSteps;
import base.BaseTest;
import data_provider.TestUIData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui_pages.kanboard.MainPage;

public class CreateProjectTest extends BaseTest {
    //    private LoginPage loginPage;
    GroupedLoginSteps groupedLoginSteps = new GroupedLoginSteps();

    @BeforeMethod
    public void setup(){
//        loginPage = new LoginPage(driver);
//        loginPage.login(new User("admin","admin"));
        groupedLoginSteps.loginViaApi("admin", "admin");
    }

    @Test(dataProvider = "project", dataProviderClass = TestUIData.class)
    public void createProjectTest(String name, String identifier, String taskLimit){
        MainPage mainPage = new MainPage(driver);
        mainPage.createProject(name, identifier, taskLimit);
        Assert.assertEquals(mainPage.getAlertSuccess().getText(),"Your project has been created successfully.");
    }
}
