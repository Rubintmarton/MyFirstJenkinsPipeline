package com.myfirstjenkinspipeline;

import com.example.pages.loginpage.LoginPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    protected Page page;
    protected LoginPage loginPage;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();

        final BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setChannel("chrome")
               .setHeadless(false);

        browser = playwright.chromium()
                            .launch(options);
    }

    @AfterAll
    static void closeBrowser() {
        if (playwright != null) {
            playwright.close();
        }
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
        loginPage = new LoginPage(page);
        pageLaunch();
    }

    @AfterEach
    void closeContext() {
        if (context != null) {
            context.close();
        }
    }

    @Step
    public void pageLaunch() {
        page.navigate("https://practicetestautomation.com/practice-test-login/");
        page.waitForLoadState(LoadState.LOAD);
    }
}