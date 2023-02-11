package com.purna.stepdefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.purna.libraries.TestContext;
import com.purna.pages.LoginPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login_StepDefinitions
{
    private WebDriver driver;
    private LoginPage onLoginPage;
    private TestContext testContext;

    public Login_StepDefinitions(TestContext context)
    {
        testContext = context;
        driver = testContext.getDriver();
        onLoginPage = testContext.getPageObjectManager().getLoginPage();
    }

    @Then("^User on Login Page enters valid username and password$")
    public void userOnLoginPageEntersValidUsersnameAndPassword() {
        onLoginPage.txtbox_username.sendKeys(testContext.getMapTestData().get("username"));
        onLoginPage.txtbox_password.sendKeys(testContext.getMapTestData().get("password"));
    }


    @When("^User clicks on Login button$")
    public void userClicksOnLogInButton() {
        onLoginPage.btn_login.click();
    }
    
    @Then("^User should navigates to sales dashboard page$")
    public void user_should_navigates_to_sales_dashboard_page() throws Throwable {
        Assert.assertEquals(driver.getCurrentUrl(), testContext.getMapTestData().get("expectedUrl"));
    }
}
