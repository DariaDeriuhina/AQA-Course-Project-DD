package api;


import api.groped_steps.GroupedLoginSteps;
import api.steps.TasksApiSteps;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui_pages.kanboard.TaskPage;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class TasksTest extends BaseTest {
    TasksApiSteps tasksApiSteps = new TasksApiSteps();
    GroupedLoginSteps groupedLoginSteps = new GroupedLoginSteps();
    TaskPage taskPage = new TaskPage(driver);

    @BeforeMethod
    public void setup() {
        groupedLoginSteps.loginViaApi("admin", "admin");
    }

    @Test()
    public void testCreateTaskRequiredParams() {
        Integer projectId = 1;
        String taskName = "myTask" + randomAlphanumeric(10);
        tasksApiSteps.createTaskRequiredParams(taskName, projectId);

        open("/board/" + projectId);

        Assert.assertTrue(tasksApiSteps.removeTask(Integer.valueOf(taskPage.findTaskId(taskName))),
                String.format("Task with name %s is not deleted", taskName));
    }
}
