package com.purna.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.purna.libraries.TestContext;
import com.purna.libraries.Utilities;
import com.purna.pages.LoginPage;

import io.cucumber.java.en.Given;

public class Common_StepDefinitions extends Utilities
{
    private WebDriver driver = null;
    private TestContext testContext = null;
    private LoginPage onLoginPage;
    
    private static Logger Log = LoggerFactory.getLogger(Common_StepDefinitions.class);
    
    public Common_StepDefinitions(TestContext context)
    {
        testContext = context;
        driver = context.getDriver();
        onLoginPage = testContext.getPageObjectManager().getLoginPage();
    }

    @Given("^User is on Purna login page \"([^\"]*)\" \"([^\"]*)\"$")
    public void userIsOnPurnaLoginPage(String testCaseID, String moduleName){
        testContext.setTestCaseID(testCaseID);
        openLoginPage(driver, testContext, testCaseID, moduleName);
        Log.info("-------------------------------------------------------------");
        Log.info("Test Case " + testCaseID + " STARTED");
    }
    
    @Given("User is logged into Purna application {string} {string}")
	public void user_is_logged_into_purna_application(String testCaseID, String moduleName) {
    	testContext.setTestCaseID(testCaseID);
        openLoginPage(driver, testContext, testCaseID, moduleName);
        Log.info("-------------------------------------------------------------");
        Log.info("Test Case " + testCaseID + " STARTED");
        
    	onLoginPage.txtbox_username.sendKeys(configProperties.getProperty("username"));
    	onLoginPage.txtbox_password.sendKeys(configProperties.getProperty("password"));
    	onLoginPage.btn_login.click();
	}
}
