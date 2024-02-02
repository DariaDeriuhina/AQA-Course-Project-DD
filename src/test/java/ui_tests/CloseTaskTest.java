package ui_tests;

import api.groped_steps.GroupedLoginSteps;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui_pages.Form.TaskForm;
import ui_pages.kanboard.BoardPage;
import ui_pages.kanboard.MainPage;
import ui_pages.kanboard.TaskPage;

public class CloseTaskTest extends BaseTest {
    private MainPage mainPage;
    private BoardPage boardPage;
    private TaskForm taskForm;
    private TaskPage taskPage;
//    private LoginPage loginPage;
    GroupedLoginSteps groupedLoginSteps = new GroupedLoginSteps();

    @BeforeMethod
        public void setupTest() {
//        loginPage = new LoginPage(driver);
//        loginPage.login(new User("admin","admin"));
        groupedLoginSteps.loginViaApi("admin", "admin");
        mainPage = new MainPage(driver);
        boardPage = mainPage.chooseProjectByIndex(1);
        boardPage.addNewTask();

        taskForm = TaskForm.newBuilder(driver, "Close Task Test")
                .withSelectedAssignee("admin")
                .build();
        taskForm.getSaveTaskBtn().click();
        taskForm.getHomeBtn().click();
    }

    @Test
    public void closeTaskTest(){
        mainPage = new MainPage(driver);
        taskPage = mainPage.chooseLastCreatedTask().closeTask();

        Assert.assertEquals(taskPage.getAlertSuccessMessage().getText(),"Task closed successfully.");

        Assert.assertEquals(taskPage.getTaskStatusElement().getText(), "closed");
    }
}
