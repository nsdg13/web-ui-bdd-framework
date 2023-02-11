package com.purna.stepdefinitions;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.purna.libraries.TestContext;
import com.purna.pages.CustDashboardPage;
import com.purna.pages.CustomerPage;
import com.purna.pages.DashboardPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Customer_StepDefinitions {
	private WebDriver driver;
    private TestContext testContext;
    private CustomerPage onCustomerPage;
    private CustDashboardPage onCustDashboardPage;
    private DashboardPage onDashboardPage;

    public Customer_StepDefinitions(TestContext context)
    {
        testContext = context;
        driver = testContext.getDriver();
        onCustomerPage = testContext.getPageObjectManager().getCustomerPage();
        onCustDashboardPage = testContext.getPageObjectManager().getCustDashboardPage();
        onDashboardPage = testContext.getPageObjectManager().getDashboardPage();
    }
	
	@When("^User clicks on Main menu$")
	public void user_clicks_on_Main_menu() {
		onDashboardPage.link_main.click();
	}

	@When("^User clicks on Customer link$")
	public void user_clicks_on_Customer_link() {
		onDashboardPage.link_customer.click();
	}

	@When("^User clicks clicks on New customer button$")
	public void user_clicks_clicks_on_New_customer_button() {
		onCustDashboardPage.btn_newCustomer.click();
	}

	@When("^User enters the customer details$")
	public void user_enters_the_customer_details(DataTable table) {
		List<List<String>> data = table.asLists();
		
		onCustomerPage.textBox_custNo.clear();
		onCustomerPage.textBox_custNo.sendKeys(data.get(0).get(0));
		onCustomerPage.textBox_custName.sendKeys(data.get(0).get(1));
		onCustomerPage.textBox_contNo.sendKeys(data.get(0).get(2));
		onCustomerPage.textBox_billAdress.sendKeys(data.get(0).get(3));
		onCustomerPage.textBox_gstNo.sendKeys(data.get(0).get(4));
	}

	@When("^User clicks on Save button$")
	public void user_clicks_on_Save_button() {
		onCustomerPage.btn_save.click();
	}

	@When("^User click on Ok button on popup$")
	public void user_click_on_Ok_button_on_popup() {
	   driver.switchTo().alert().accept();
	}

	@When("^User clicks on Customer details button$")
	public void user_clicks_on_Customer_details_button() {
		onCustomerPage.btn_custDetails.click();
	}

	@Then("^User verfies that customer is created$")
	public void user_verfies_that_customer_is_created() {
	   Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Sachin']")).getText(), "Sachin");
	}

}
