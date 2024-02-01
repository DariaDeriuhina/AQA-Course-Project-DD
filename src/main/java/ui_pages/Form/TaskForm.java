package ui_pages.Form;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui_pages.BasePage;

import static com.codeborne.selenide.Selenide.*;

@Getter
public class TaskForm extends BasePage {
    private SelenideElement title;
    private SelenideElement description;
    private SelenideElement columnDropdown;
    private SelenideElement colorDropdown;
    private SelenideElement assigneeDropdown;
    private SelenideElement priorityDropdown;
    private SelenideElement saveTaskBtn = $x("//button[@type='submit']");;


    public TaskForm(Builder builder) {
        super(builder.driver);
        this.title = builder.title;
        this.description = builder.description;
        this.columnDropdown = builder.columnDropdown;
        this.colorDropdown = builder.colorDropdown;
        this.assigneeDropdown = builder.assigneeDropdown;
        this.priorityDropdown = builder.priorityDropdown;
    }

    public static Builder newBuilder(WebDriver driver, String title) {
        return new Builder(driver, title);
    }

    public static class Builder {
        private WebDriver driver;
        private SelenideElement title;
        private SelenideElement description;
        private SelenideElement columnDropdown;
        private SelenideElement colorDropdown;
        private SelenideElement assigneeDropdown;
        private SelenideElement priorityDropdown;


        private Builder(WebDriver driver, String title) {
            this.driver = driver;
            this.title = $(By.id("form-title"));
            this.title.sendKeys(title);
        }

        public Builder withDescription(String description) {
            this.description = $(By.name("description"));
            return this;
        }

        public Builder withSelectedColumn(String column) {
            this.columnDropdown = $x("//select[@id='form-column_id']");
            columnDropdown.selectOptionContainingText(column);
            return this;
        }

        public Builder withSelectedColor(String color) {
            this.colorDropdown = $x("//select[@id='form-color_id']");
            colorDropdown.selectOptionContainingText(color);
            return this;
        }

        public Builder withSelectedAssignee(String assignee) {
            this.assigneeDropdown = $x("//select[@id='form-owner_id']");
            assigneeDropdown.selectOptionContainingText(assignee);
            return this;
        }

        public Builder withSelectedPriority(String priority) {
            this.priorityDropdown = $x("//select[@id='form-priority']");
            priorityDropdown.selectOptionContainingText(priority);
            return this;
        }

        public TaskForm build() {
            if (title == null) {
                throw new IllegalStateException("Title is required for TaskForm");
            }
            return new TaskForm(this);
        }
    }
}


