package ui_pages.kanboard;

import com.codeborne.selenide.SelenideElement;
import entity.User;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui_pages.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private SelenideElement userNameField = $x("//input[@id='form-username']");
    private SelenideElement passwordField = $("#form-password");
    private SelenideElement signInBtn = $(".btn.btn-blue");
    private SelenideElement rememberMeCheckbox = $(By.name("remember_me"));
    private SelenideElement forgotPasswordLink = $("a[href='forgot-password']");
    private SelenideElement errorAlert = $(".alert.alert-error");

    public MainPage login(User user) {
        fillField(userNameField, user.getLogin());
        fillField(passwordField, user.getPassword());
        signInBtn.click();
        return new MainPage(driver);
    }

    public LoginPage isLoginFailed(){
        errorAlert.shouldBe(visible);
        return new LoginPage(driver);
    }

    public SelenideElement getErrorAlert() {
        return errorAlert;
    }
}
