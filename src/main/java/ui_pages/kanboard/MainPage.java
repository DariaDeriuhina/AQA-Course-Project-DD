package ui_pages.kanboard;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui_pages.BasePage;
import ui_pages.Form.ProjectForm;

import static com.codeborne.selenide.Selenide.*;

@Getter
public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    private SelenideElement pageTitle = $(".title-container");
    private SelenideElement createNewProjectBtn = $(By.linkText("New project"));
    private SelenideElement saveProjectBtn = $x("//div[@class='form-actions']/button");
    private SelenideElement alertSuccess = $x("//div[contains(@class, 'alert-success')]");
    private SelenideElement myProjectSection = $("a[href='/dashboard/1/projects']");
    private SelenideElement myTaskSection = $("a[href='/dashboard/1/tasks']");
    private ElementsCollection tableRows = $$x("//div[contains(@class, 'table-list-row')]");

    ProjectForm projectForm = new ProjectForm(driver);


    public SelenideElement getPageTitle() {
        return pageTitle;
    }

    public void createProject(String name, String identifier, String taskLimit) {
        createNewProjectBtn.click();
        projectForm.fillProjectForm(name, identifier, taskLimit);
        saveProjectBtn.click();
    }

    public BoardPage chooseProjectByIndex(int index) {
        tableRows.get(index).$(".table-list-title").click();
        return new BoardPage(driver);
    }

    public TaskPage chooseTaskByIndex(int index) {
        myTaskSection.click();
        tableRows.get(index).$(".table-list-title").click();
        return new TaskPage(driver);
    }

    public TaskPage chooseLastCreatedTask() {
        myTaskSection.click();
        tableRows.last().$(".table-list-title").click();
        return new TaskPage(driver);
    }
}
