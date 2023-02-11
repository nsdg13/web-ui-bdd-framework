package com.purna.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SupplierDashboardPage {
	
	public SupplierDashboardPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name="btn_new_party")
	public WebElement btn_newSupplier;

}
