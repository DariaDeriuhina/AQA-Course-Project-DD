package ui_pages.kanboard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui_pages.BasePage;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Getter
public class TaskPage extends BasePage {

    public TaskPage(WebDriver driver) {
        super(driver);
    }

    SelenideElement taskTitle = $("#task-summary");
    SelenideElement closeTaskLink = $("a[href*='close']");
    SelenideElement addCommentTaskLink = $("a[href*='comment']");
    SelenideElement alertInfoMessage = $x("//p[contains(@class, 'alert-info')]");
    SelenideElement alertSuccessMessage = $x("//div[contains(@class, 'alert-success')]");
    SelenideElement confirmBtn = $("#modal-confirm-button");
    SelenideElement taskStatusElement = $x("//ul[@class='no-bullet']/li[strong='Status:']/span");
    SelenideElement commentSection = $x(
            "//div[@id='modal-content']//textarea[@name='comment']");
    SelenideElement saveButton = $x(
            "//div[@id='modal-content']//button[@type='submit']");
    ElementsCollection comments = $$x("//div[@class='markdown']/p");

    public TaskPage closeTask() {
        closeTaskLink.click();
        alertInfoMessage.shouldBe(visible);
        confirmBtn.click();
        return new TaskPage(driver);
    }

    public TaskPage addComment(String comment) {
        addCommentTaskLink.click();
        commentSection.setValue(comment);
        saveButton.click();
        return new TaskPage(driver);
    }

    public boolean isCommentPresent(String taskComment) {
        if (comments != null) {
            for (SelenideElement comment : comments) {
                String text = comment.getText();
                if (text != null && text.contains(taskComment)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String findTaskId(String taskName) {
        String taskUrl = taskLink(taskName).getAttribute("href");
        String[] urlParts = taskUrl.split("/");
        return urlParts[urlParts.length - 1];
    }

    private SelenideElement taskLink(String taskName) {
        return $x("//div[@class='task-board-title']/a[text()='" + taskName + "']");
    }
}
