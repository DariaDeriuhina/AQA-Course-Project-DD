package api.tests;

import api.groped_steps.GroupedLoginSteps;
import api.steps.ProjectAPISteps;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class ProjectTests extends BaseTest {
    GroupedLoginSteps groupedLoginSteps = new GroupedLoginSteps();
    ProjectAPISteps projectAPISteps = new ProjectAPISteps();

    @BeforeMethod
    public void setUpMethod(){
        groupedLoginSteps.loginViaApi("admin", "admin");
    }

    @Test()
    public void createProjectTest() {
        String projectName = "myProject" + randomAlphanumeric(10);
        int projectId = Integer.parseInt(projectAPISteps.createProject(projectName));


        open("/project/" + projectId);

        Assert.assertTrue(projectAPISteps.removeProject(projectId),
                String.format("Project with ID %s is not deleted", projectId));
    }
}





