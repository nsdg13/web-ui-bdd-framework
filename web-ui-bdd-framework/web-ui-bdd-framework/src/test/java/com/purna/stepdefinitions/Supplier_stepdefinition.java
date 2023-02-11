package com.purna.stepdefinitions;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.purna.libraries.TestContext;
import com.purna.pages.DashboardPage;
import com.purna.pages.SupplierDashboardPage;
import com.purna.pages.SupplierPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Supplier_stepdefinition {
	WebDriver driver;
	private TestContext testContext;
	private DashboardPage onDashboardPage;
	private SupplierDashboardPage onSupplierDashboardPage;
	private SupplierPage onSupplierPage;
	
	
	public Supplier_stepdefinition(TestContext context)
    {
        testContext = context;
        driver = testContext.getDriver();
        onDashboardPage =  testContext.getPageObjectManager().getDashboardPage(); 
        onSupplierDashboardPage = testContext.getPageObjectManager().getSupplierDashboardPage();
        onSupplierPage = testContext.getPageObjectManager().getSupplierPage();
    }

	@When("User clicks on Main Menu")
	public void user_clicks_on_main_menu() {
		onDashboardPage.link_main.click();
	}

	@When("User clicks on supplier link")
	public void user_clicks_on_supplier_link() {
		onDashboardPage.link_supplier.click();
	}

	@When("User clicks on New Supplier button")
	public void user_clicks_on_new_supplier_button() {
		onSupplierDashboardPage.btn_newSupplier.click();
	}

	@When("User enters below supplier details")
	public void user_enters_below_supplier_details(DataTable dataTable) {
		List<List<String>> supplierData = dataTable.asLists();
		
		onSupplierPage.txtBox_supplierName.sendKeys(supplierData.get(0).get(0));
		onSupplierPage.txtBox_contactNoSupplier.sendKeys(supplierData.get(0).get(1));
		onSupplierPage.txtBox_address.sendKeys(supplierData.get(0).get(2));
		onSupplierPage.txtBox_emailId.sendKeys(supplierData.get(0).get(3));
		onSupplierPage.txtBox_contactPerson.sendKeys(supplierData.get(0).get(4));
		onSupplierPage.txtBox_gstNo.sendKeys(supplierData.get(0).get(5));
		onSupplierPage.txtBox_panNo.sendKeys(supplierData.get(0).get(6));
	}

	@When("User clicks on supplier Save button")
	public void user_clicks_on_save_button() {
		onSupplierPage.btn_save.click();
		driver.switchTo().alert().accept();
	}

	@When("User clicks on Supplier details button")
	public void user_clicks_on_supplier_details_button() {
		onSupplierPage.btn_supplierDetails.click();
	}

	@Then("Supplier should be created")
	public void supplier_should_be_created() {
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='John']")).getText(), "John");
	}
	
}
