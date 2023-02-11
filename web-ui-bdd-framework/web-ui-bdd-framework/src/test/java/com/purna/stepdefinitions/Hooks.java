package com.purna.stepdefinitions;

import com.purna.libraries.PageObjectManager;
import com.purna.libraries.TestContext;
import com.purna.libraries.Utilities;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends Utilities
{
    private TestContext testContext;

    public Hooks(TestContext context)
    {
        testContext = context;
    }

    @Before
    public void setUp(Scenario scenario)
    {
        if (testContext.getDriver() == null)
        {
            testContext.setDriver(getWebDriver());
        }
        testContext.setPageObjectManager(new PageObjectManager(testContext.getDriver()));
        testContext.setScenario(scenario);
    }
    @After
    public void cleanUp()
    {
        closeBrowser(testContext);
    }

}
