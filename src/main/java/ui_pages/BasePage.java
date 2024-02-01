package ui_pages;

import java.time.Duration;
import java.util.List;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage {
    protected WebDriver driver;
    protected SelenideElement homeBtn = $("a[href='/dashboard']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // Common methods for interacting with page elements
    protected void clickOnElement(By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
    }

    protected void enterText(By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.sendKeys(text);
    }

    protected String getElementText(By locator) {
        WebElement element = driver.findElement(locator);
        return element.getText();
    }

    protected WebElement waitForElement(By by, int time){
        return new WebDriverWait(driver, Duration.ofSeconds(time)).until(ExpectedConditions.presenceOfElementLocated(by));
    }


    protected List<WebElement> waitForElements(By by, int time){
        return new WebDriverWait(driver, Duration.ofSeconds(time)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    protected boolean waitTillDisappear(By by, int time){
        return new WebDriverWait(driver, Duration.ofSeconds(time)).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected void fillField(SelenideElement field, String text){
        field.shouldBe(visible).clear();
        field.sendKeys(text);
    }

    public SelenideElement getHomeBtn() {
        return homeBtn;
    }
}
