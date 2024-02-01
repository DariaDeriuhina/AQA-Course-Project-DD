package ui_pages.Form;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui_pages.BasePage;
import ui_pages.kanboard.MainPage;

import static com.codeborne.selenide.Selenide.$;

public class ProjectForm extends BasePage {

    public ProjectForm(WebDriver driver) {
        super(driver);
    }

    private SelenideElement projectNameField = $("#form-name");
    private SelenideElement projectIdentifierField = $("#form-identifier");
    private SelenideElement swimlaneTaskLimitsCheckbox = $(By.name("per_swimlane_task_limits"));
    private SelenideElement taskLimitField = $("#form-task_limit");


    public MainPage fillProjectForm(String name, String identifier, String taskLimit) {
        fillField(projectNameField, name);
        fillField(projectIdentifierField, identifier);
        fillField(taskLimitField, taskLimit);
        return new MainPage(driver);
    }

}
