package com.myfirstjenkinspipeline;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.Test;

public class LoginPageTest extends BaseTest {

    @Test
    public void positiveLoginTest() {
        loginPage
            .inputUsername("student")
            .inputPassword("Password123")
            .clickSubmit();

        PlaywrightAssertions.assertThat(loginPage.getLoginSuccessMessage()).isVisible();
        loginPage.verifyUrl();
    }

    @Test
    public void negativeUsernameTest() {
        loginPage
            .inputUsername("Incorrect Username")
            .inputPassword("Password123")
            .clickSubmit();

        PlaywrightAssertions.assertThat(loginPage.getInvalidUsernameMessage()).isVisible();
    }

    @Test
    public void negativePasswordtest() {
        loginPage
            .inputUsername("student")
            .inputPassword("Incorrect Password")
            .clickSubmit();

        PlaywrightAssertions.assertThat(loginPage.getInvalidPasswordMessage()).isVisible();

    }
}