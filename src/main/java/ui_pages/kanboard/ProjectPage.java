package ui_pages.kanboard;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import ui_pages.BasePage;

import static com.codeborne.selenide.Selenide.*;

public class ProjectPage extends BasePage {
    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    private SelenideElement title = $(".title");
    private SelenideElement sideBar = $(".sidebar");
    private ElementsCollection summaryPanel = $$(".panel");
    private SelenideElement closeProjectLink = $("a[href='/project/1/disable']");
    private SelenideElement yesBnt = $("#modal-confirm-button");

}
