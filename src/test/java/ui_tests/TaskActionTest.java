package ui_tests;

import api.groped_steps.GroupedLoginSteps;
import base.BaseTest;
import data_provider.TestUIData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui_pages.Form.TaskForm;
import ui_pages.kanboard.BoardPage;
import ui_pages.kanboard.MainPage;
import ui_pages.kanboard.TaskPage;

import static com.codeborne.selenide.Condition.visible;


public class TaskActionTest extends BaseTest {
    private MainPage mainPage;
    private BoardPage boardPage;
    private TaskPage taskPage;
    private TaskForm taskForm;
//    private LoginPage loginPage;
    GroupedLoginSteps groupedLoginSteps = new GroupedLoginSteps();

    @BeforeMethod
    public void setup() {
//        loginPage = new LoginPage(driver);
//        loginPage.login(new User("admin","admin"));
        groupedLoginSteps.loginViaApi("admin", "admin");
    }

    @Test(dataProvider = "taskData", dataProviderClass = TestUIData.class)
    public void createTaskTest(String taskName) {
        mainPage = new MainPage(driver);
        boardPage = mainPage.chooseProjectByIndex(1);
        boardPage.addNewTask();

        taskForm = TaskForm.newBuilder(driver, taskName)
                .withSelectedPriority("1")
                .withSelectedColumn("Backlog")
                .withSelectedAssignee("admin")
                .build();
        taskForm.getSaveTaskBtn().click();

        Assert.assertEquals(boardPage.getAlertSuccess().getText(),"Task created successfully.");

        boardPage.findTaskInColumn(taskName).shouldBe(visible);
    }

    @Test(dataProvider = "commentData", dataProviderClass = TestUIData.class)
    public void addCommentTaskTest(String comment){
        mainPage = new MainPage(driver);
        taskPage = mainPage.chooseLastCreatedTask().addComment(comment);

        Assert.assertEquals(taskPage.getAlertSuccessMessage().getText(),"Comment added successfully.");

        Assert.assertTrue(taskPage.isCommentPresent(comment));
    }
}

