package ui_pages.kanboard;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import ui_pages.BasePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@Getter
public class BoardPage extends BasePage {
    public BoardPage(WebDriver driver) {
        super(driver);
    }

    private SelenideElement menuDropdownIcon = $x("//a[@title='Configure this project']");
    private SelenideElement addNewTaskLink = $x("//ul[@class='dropdown-submenu-open']" +
            "//*[contains(text(), 'Add a new task')]");
    private SelenideElement taskOnBoard = $( ".task-board-expanded");
    private SelenideElement alertSuccess = $x("//div[contains(@class, 'alert-success')]");
    private ElementsCollection taskTicket = $$("[class*=task-board-status-open]");

    public SelenideElement findTaskInColumn(String searchText) {
        return taskTicket.last().$(".task-board-title");
    }

    public void addNewTask() {
        menuDropdownIcon.click();
        addNewTaskLink.click();
    }

}
