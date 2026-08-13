package com.example.pages.loginpage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;
import lombok.Getter;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {
    private final Page page;
    private final Locator userNameInput;
    private final Locator passwordInput;
    private final Locator submitButton;
    @Getter
    private final Locator loginSuccessMessage;
    @Getter
    private final Locator invalidUsernameMessage;
    @Getter
    private final Locator invalidPasswordMessage;

    public LoginPage(Page page) {
        this.page = page;
        this.userNameInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username"));
        this.passwordInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password"));
        this.submitButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit"));
        this.loginSuccessMessage = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Logged In Successfully"));
        this.invalidUsernameMessage = page.locator("#error").getByText("Your username is invalid!");
        this.invalidPasswordMessage = page.locator("#error").getByText("Your password is invalid!");
    }

    @Step
    public LoginPage inputUsername(String username) {
        userNameInput.fill(username);
        return this;
    }

    @Step
    public LoginPage inputPassword(String password) {
        passwordInput.fill(password);
        return this;
    }

    @Step
    public void clickSubmit() {
        submitButton.click();
//        assertThat(page).hasURL(Pattern.compile(".*logged-in-successfully/?$"));
    }

    @Step
    public void verifyUrl() {
        assertThat(page).hasURL(Pattern.compile(".*logged-in-successfully/?$"));
    }
}